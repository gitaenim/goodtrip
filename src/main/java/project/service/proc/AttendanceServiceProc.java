package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;

import project.domain.DTO.AttendanceRegDTO;
import project.domain.repository.AttendanceRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.security.MyUserDetails;
import project.service.AttendanceService;

public class AttendanceServiceProc implements AttendanceService{

	@Autowired
	private AttendanceRepository attRepo;
	
	@Autowired
	private EmployeesEntityRepository emRepo;

	@Override
	public void saveAttIn(String timeData, long no, AttendanceRegDTO attendanceRegDTO) {
		attRepo.save(attendanceRegDTO.toDailyWorkingHoursEntity()
				.
		
	}

//	
//	reviewRepo.save(reviewRegDTO.toReviewEntity()
//			.item(itemRepo.findById(no).orElseThrow())
//			.member(memRepo.findByEmail(email).orElseThrow()));



}
