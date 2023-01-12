package project.service.proc;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	//퇴근시간 저장 230111 안나 작성
	@Transactional
	@Override
	public void saveAttOut(long no, AttendanceRegClockOutDTO attendanceRegOutDTO) {
		
		Optional<DailyWorkingHoursEntity> result= attRepo.findAllByClockInBetween(startDatetime, endDatetime);
		DailyWorkingHoursEntity entity=result.get();
		entity.update(attendanceRegOutDTO);
		//System.out.println("result : " + result);
	}
	
	//출퇴근시간 가져오기 230111 안나 작성
	@Override
	public void attenList(long no, Model model) {
		EmployeesEntity emp = emRepo.findById(no).orElseThrow();
		List<DailyWorkingHoursEntity> list = attRepo.findByEmployeeNoAndClockInBetween(emp, startDatetime, endDatetime);
		model.addAttribute("list",list);
		//System.out.println("list: " + list);
	}

	//전체 근태 리스트 230111 안나 작성 : 휴가 미설정
	@Override
	public void listAtt(Model model) {
		model.addAttribute("attListEmp",emRepo.findAll().stream().map(AttendanceListEmpDTO::new)
				.collect(Collectors.toList()));
		model.addAttribute("attList", attRepo.findAllByClockInBetween(startDatetime, endDatetime).stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
	}

	//내 근태+휴가 230111 안나 작성 : 휴가 미설정
	@Override
	public void myListAtt(long no, Model model) {
		model.addAttribute("myAttList",attRepo.findByEmployeeNo(emRepo.findById(no).orElseThrow())
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
		
	}

	//내 근태만 리스트 230111 안나 작성
	@Override
	public void myListAttOnly(long no, Model model) {
		model.addAttribute("myAttList",attRepo.findByEmployeeNo(emRepo.findById(no).orElseThrow())
				.stream()
				.map(AttendanceMyListDTO::new)
				.collect(Collectors.toList()));
	}

	//사원별 근태 + 휴가 리스트 230111 안나 작성 : 휴가 미설정
	@Override
	public void personalAtt(long no, Model model) {
		model.addAttribute("attListEmp",emRepo.findById(no).stream().map(AttendanceListEmpDTO::new)
				.collect(Collectors.toList()));
		model.addAttribute("attList", attRepo.findByEmployeeNo(emRepo.findById(no).orElseThrow()).stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));
		
	}

	//사원별 근태만 리스트 230111 안나 작성
	@Override
	public void personalWork(long no, Model model) {
		model.addAttribute("attListEmp",emRepo.findById(no).stream().map(AttendanceListEmpDTO::new)
				.collect(Collectors.toList()));
		model.addAttribute("attList", attRepo.findByEmployeeNo(emRepo.findById(no).orElseThrow()).stream().map(AttendanceListDTO::new)
				.collect(Collectors.toList()));		
	}
}