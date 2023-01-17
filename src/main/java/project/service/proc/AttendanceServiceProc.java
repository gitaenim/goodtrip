package project.service.proc;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.AttendanceListDTO;
import project.domain.DTO.AttendanceListEmpDTO;
import project.domain.DTO.AttendanceMyListDTO;
import project.domain.DTO.AttendanceRegClockInDTO;
import project.domain.DTO.AttendanceRegClockOutDTO;
import project.domain.entity.DailyWorkingHoursEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.AttendanceRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.AttendanceService;

@Service
public class AttendanceServiceProc implements AttendanceService{

	//오늘 날짜 BETWEEN 검색용 변수 230111 안나 작성
	LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
	LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23,59,59));
	
	@Autowired
	private AttendanceRepository attRepo;
	
	@Autowired
	private EmployeesEntityRepository emRepo;

	//출근시간 저장 230111 안나 작성
	@Transactional
	@Override
	public void saveAttIn(long no, AttendanceRegClockInDTO attendanceRegInDTO) {		
		//DTO에 저장
		attRepo.save(attendanceRegInDTO.clockInToDailyWorkingHoursEntity()
				.employee(emRepo.findById(no).orElseThrow())
				);
	}

	//퇴근시간 저장 230111 안나 작성 230112 안나 수정
	@Transactional
	@Override
	public void saveAttOut(long no, AttendanceRegClockOutDTO attendanceRegOutDTO) {
		EmployeesEntity emp = emRepo.findById(no).orElseThrow();
		Optional<DailyWorkingHoursEntity> result= attRepo.findByClockInBetweenAndEmployeeNo(startDatetime, endDatetime, emp);
		DailyWorkingHoursEntity entity=result.get();
		entity.update(attendanceRegOutDTO);
		System.out.println("result : " + result);
	}
	
	//출퇴근시간 가져오기 230111 안나 작성
	@Override
	public void attenList(long no, Model model) {
		EmployeesEntity emp = emRepo.findById(no).orElseThrow();
		List<DailyWorkingHoursEntity> list = attRepo.findByEmployeeNoAndClockInBetween(emp, startDatetime, endDatetime);
		model.addAttribute("list",list);
		//System.out.println("list: " + list);
	}

	//전체 근태 리스트 230115 안나 작성 : 휴가 미설정
	@Override
	public void listAtt(Model model) {
		
		List<EmployeesEntity> listEmployees = emRepo.findAll(); // 전체 리스트 가져오기
		List<AttendanceListDTO> listEmployeesAtts = attRepo.findAllByClockInBetween(startDatetime, endDatetime) // 오늘날짜에 출근버튼 누른 사람 전체 리스트
				.stream().map(AttendanceListDTO::new).collect(Collectors.toList()); //AttendanceListDTO에 담기
		List<AttendanceListDTO> setAttList = new ArrayList<>(); // 빈 배열

		for( EmployeesEntity employee : listEmployees ){

		   for( AttendanceListDTO work : listEmployeesAtts ){
		      
		      if(employee.getNo() == work.getEmployeeNo()){
		    	  setAttList.add(work); //전사 리스트와 출결리스트에 일치하는 이름이 있는경우 리스트에 담기
		      }
		      else{
		         //출근 버튼 안 누른 사람은 업무시간 : "-", 출근상태 : "미출근" 으로 뜨게끔
		    	  setAttList.add( AttendanceListDTO.builder().employeeNo(employee.getNo()).workingHour("-").status("미출근").build() );
		      } 
		   }
		}

		model.addAttribute("list", setAttList); // 업무시간 및 상태
		
		model.addAttribute("attListAll",emRepo.findAll().stream().map(AttendanceListEmpDTO::new)
				.collect(Collectors.toList())); // 전체 직원 리스트 뿌려주기

	}

	//내 근태+휴가 230111 안나 작성 : 휴가 미설정 / 230117 페이징 추가 안나
	@Override
	public void myListAtt(long no, Model model,Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployeeNoOrderByDateDesc(emRepo.findById(no).orElseThrow(), pageable);
				
		model.addAttribute("myAttList",pageResult
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 페이징 10개까지 보여줄거야
	}

	//내 근태만 리스트 230111 안나 작성 / 230117 페이징 추가 안나
	@Override
	public void myListAttOnly(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployeeNoOrderByDateDesc(emRepo.findById(no).orElseThrow(), pageable);
		
		model.addAttribute("myAttList",pageResult
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 페이징 10개까지 보여줄거야
	}

	//사원별 근태 + 휴가 리스트 230111 안나 작성 : 휴가 미설정 / 230117 페이징 추가 안나
	@Override
	public void personalAtt(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployeeNoOrderByDateDesc(emRepo.findById(no).orElseThrow(), pageable);
		
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 페이징 10개까지 보여줄거야
		model.addAttribute("emNo", no);
		
	}

	//사원별 근태만 리스트 230111 안나 작성 / 230117 페이징 추가 안나
	@Override
	public void personalWork(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployeeNoOrderByDateDesc(emRepo.findById(no).orElseThrow(), pageable);
				
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 페이징 10개까지 보여줄거야
		model.addAttribute("emNo", no);
	}
}