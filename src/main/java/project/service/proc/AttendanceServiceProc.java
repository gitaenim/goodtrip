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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.AttendanceListDTO;
import project.domain.DTO.AttendanceListEmpDTO;
import project.domain.DTO.AttendanceMyListDTO;
import project.domain.DTO.AttendanceRegClockInDTO;
import project.domain.DTO.AttendanceRegClockOutDTO;
import project.domain.entity.DailyWorkingHoursEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.AttendanceRepository;
import project.domain.repository.DepartmentsEntityRepository;
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
	
	@Autowired
	private DepartmentsEntityRepository departmentRepo;
	
	//페이징 메서드
	private void pagingE(Model model, Page<EmployeesEntity> result) {
		model.addAttribute("pageNum", result.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", result.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", result.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 내비게이션 숫자 총 몇개로 할건지 : 1 ~ 10까지 10개 보여줄거야
	}
	
	//페이징 메서드2
	private void pagingD(Model model, Page<DailyWorkingHoursEntity> pageResult) {
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 내비게이션 숫자 총 몇개로 할건지 : 1 ~ 10까지 10개 보여줄거야
	}

	//출근시간 저장 230111 안나 작성
	@Transactional
	@Override
	public void saveAttIn(long no, AttendanceRegClockInDTO attendanceRegInDTO) {		
		//DTO에 저장
		Optional<DailyWorkingHoursEntity> result= attRepo.findByEmployee_noAndClockInBetween(no, startDatetime, endDatetime);

		if(result.isEmpty()) { 	//데이터가 없으면 세이브
			attRepo.save(attendanceRegInDTO.clockInToDailyWorkingHoursEntity()
			.employee(emRepo.findById(no).orElseThrow())
			);					
		} else { //데이터가 있으면 업데이트
			DailyWorkingHoursEntity entity=result.get();
			entity.update(attendanceRegInDTO);
		}
				
	}

	//퇴근시간 저장 230111 안나 작성 230112 안나 수정
	@Transactional
	@Override
	public void saveAttOut(long no, AttendanceRegClockOutDTO attendanceRegOutDTO) {
		Optional<DailyWorkingHoursEntity> result= attRepo.findByEmployee_noAndClockInBetween(no, startDatetime, endDatetime);
		DailyWorkingHoursEntity entity=result.get();
		entity.update(attendanceRegOutDTO);
	}
	
	//출퇴근시간 가져오기 230111 안나 작성
	@Override
	public void attenList(long no, Model model) {
		List<DailyWorkingHoursEntity> list = attRepo.findByClockInBetweenAndEmployee_no(startDatetime, endDatetime, no);
		model.addAttribute("list",list);
	}

	//전체 근태 리스트 230115 안나 작성 : 휴가 미설정
	@Override
	public void listAtt(Model model, Pageable pageable, String keyword) {
		
		//int pageNo=1,size=10;
		//Pageable page=PageRequest.of(pageNo-1, size, Direction.DESC, "no");
		
		Page<EmployeesEntity> result = null;
		
		int check;//페이징 체크용..
		
		if(keyword == null) {//검색어가 없을 때
			result=emRepo.findAllByDeleteStatus(false, pageable);
			check = 1;
		} else {//검색어가 있을 때
			result=emRepo.findAllByDeleteStatusAndNameContaining(false, keyword, pageable);
			check = 2;
		}
		
		model.addAttribute("list", result.stream()
			.map(e->new AttendanceListDTO(attRepo.findByEmployee_noAndClockInBetween(e.getNo(),startDatetime,endDatetime)
					//출근하지 않은사원은 빈객체생성
					.orElseGet(()->attRepo.save(DailyWorkingHoursEntity.builder().date(LocalDate.now()).status("미출근").employee(e).build())))
			)
			.collect(Collectors.toList()) );
		
		pagingE(model, result);//페이징
		model.addAttribute("check", check);
		
		model.addAttribute("department", departmentRepo.findAll());//부서 이름 뿌려줄거임
		
		model.addAttribute("keyword", keyword);

	}
	
	//전체 근태리스트 뿌려주기 - 부서별 검색
	@Override
	public void findAllByDepartmentNo(Model model, Long department, Pageable pageable) {
		Page<EmployeesEntity> result=emRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatus(department, false, pageable);
		model.addAttribute("list2", result.stream()
				.map(e->new AttendanceListDTO(attRepo.findByEmployee_noAndClockInBetween(e.getNo(),startDatetime,endDatetime)
						//출근하지 않은사원은 빈객체생성
						.orElseGet(()->attRepo.save(DailyWorkingHoursEntity.builder().date(LocalDate.now()).status("미출근").employee(e).build())))
				)
				.collect(Collectors.toList()) );
		
		boolean nullcheck = false;
		if(result.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);
		
		pagingE(model, result);//페이징
	}

	//내 근태+휴가 230111 안나 작성 : 휴가 미설정 / 230117 페이징 추가 안나
	@Override
	public void myListAtt(long no, Model model,Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noOrderByDateDesc(no, pageable);
				
		model.addAttribute("myAttList",pageResult
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
		
		pagingD(model, pageResult);//페이징
	}

	//내 근태만 리스트 230111 안나 작성 / 230117 페이징 추가 안나
	@Override
	public void myListAttOnly(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noOrderByDateDesc(no, pageable);
		
		model.addAttribute("myAttList",pageResult
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
		
		pagingD(model, pageResult);//페이징
	}

	//사원별 근태 + 휴가 리스트 230111 안나 작성 : 휴가 미설정 / 230117 페이징 추가 안나
	@Override
	public void personalAtt(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noOrderByDateDesc(no, pageable);
		
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("attListEmp",emRepo.findAll());
		model.addAttribute("emNo", no);

		pagingD(model, pageResult);//페이징
		
	}
	
	//사원별 근태 + 230119 날짜별 검색 안나
	@Override
	public void personalAttSearch(long no, Model model, Pageable pageable, LocalDate dateStart, LocalDate dateEnd) {
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noAndDateBetweenOrderByDateDesc(no, dateStart, dateEnd, pageable);
		
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("attListEmp",emRepo.findAll());
		model.addAttribute("emNo", no);
		model.addAttribute("dateStart", dateStart);
		model.addAttribute("dateEnd", dateEnd);
		model.addAttribute("check", 2);

		pagingD(model, pageResult);//페이징
		
	}

	//사원별 근태만 리스트 230111 안나 작성 / 230117 페이징 추가 안나
	@Override
	public void personalWork(long no, Model model, Pageable pageable) {
		
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noOrderByDateDesc(no, pageable);
				
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("attListEmp",emRepo.findAll());
		model.addAttribute("emNo", no);
		
		pagingD(model, pageResult);//페이징
		
	}

	//사원별 근태만 리스트 + 230119 날짜별 검색 안나
	@Override
	public void personalWorkingDaySearch(long no, Model model, Pageable pageable, LocalDate dateStart,
			LocalDate dateEnd) {
		Page<DailyWorkingHoursEntity> pageResult = attRepo.findByEmployee_noAndDateBetweenOrderByDateDesc(no, dateStart, dateEnd, pageable);
		
		model.addAttribute("attList", pageResult
				.stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
		model.addAttribute("attListEmp",emRepo.findAll());
		model.addAttribute("emNo", no);
		model.addAttribute("dateStart", dateStart);
		model.addAttribute("dateEnd", dateEnd);
		model.addAttribute("check", 2);
		
		pagingD(model, pageResult);//페이징
		
	}


}