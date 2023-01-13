package project.service.proc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.ScheduleInsertDTO;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ScheduleEntity;
import project.domain.repository.EmployeesEntityRepository;
import project.domain.repository.ScheduleEntityRepository;
import project.service.ScheduleService;

@Service
public class ScheduleServiceProc implements ScheduleService {

	@Autowired
	EmployeesEntityRepository employeesRepository;
	
	@Autowired
	ScheduleEntityRepository scheduleRepository;
	
	// 스케줄을 데이터를 저장해 주는 기능
	@Override
	public void save(ScheduleInsertDTO dto, long empNo) {
		
		// 로그인한 회원 정보 조회
		EmployeesEntity emp = employeesRepository.findById(empNo).orElseThrow();
		
		// 조회한 회원의 스케줄 정보 저장 기능
		scheduleRepository.save(dto.toSceduleEntityForSave(emp));
	}
	// 스케줄 정보 전체 조회후 반환해 주는 기능
	@Override
	public List<ScheduleEntity> findAll() {
		return scheduleRepository.findAll();
	}

}
