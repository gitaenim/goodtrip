package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.AttendanceRegDTO;
import project.domain.repository.AttendanceRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.AttendanceService;

@Service
public class AttendanceServiceProc implements AttendanceService{

	@Autowired
	private AttendanceRepository attRepo;
	
	@Autowired
	private EmployeesEntityRepository emRepo;

	@Override
	public void saveAttIn(String timeData, long no, AttendanceRegDTO attendanceRegDTO) {
		attRepo.save(attendanceRegDTO.toDailyWorkingHoursEntity());
				//수정중이였던거 같음
	}

//	
//	reviewRepo.save(reviewRegDTO.toReviewEntity()
//			.item(itemRepo.findById(no).orElseThrow())
//			.member(memRepo.findByEmail(email).orElseThrow()));



}
