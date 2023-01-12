package project.service.proc;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.DayOffInsertDTO;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.DayOffEntityRepository;
import project.domain.repository.DaysOffNumbersEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.DayOffService;

@Service
public class DayOffServiceProcess implements DayOffService {

	@Autowired
	private DayOffEntityRepository dayOffRepo;
	
	@Autowired
	private EmployeesEntityRepository employeesRepo; 
	
	@Autowired
	private DaysOffNumbersEntityRepository daysOffNumbersRepo;
	
	//휴가 등록
	@Override
	public void save(DayOffInsertDTO dto) {
		
		EmployeesEntity emp=employeesRepo.findById(dto.getEmployeeNo()).orElseThrow();
		DaysOffNumbersEntity numbers=daysOffNumbersRepo.findByNo(emp);		
		if(numbers==null) {
			numbers=daysOffNumbersRepo.save(DaysOffNumbersEntity.builder()
				 .no(emp).totalDays(15).useDays(dto.getUseDays()).build()); 
		}
		
		dayOffRepo.save(dto.toDayOffEntity(emp));		
	}
	
	//휴가 신청일수 업데이트
	@Override
	public void update(DayOffInsertDTO dto) {
		//findAllByEmployeeNo(dto.getEmployeeNo());
		EmployeesEntity emp = employeesRepo.findById(dto.getEmployeeNo()).orElseThrow();
		List<DayOffEntity> day = dayOffRepo.findByEmployeeNo(emp);
		DaysOffNumbersEntity use = daysOffNumbersRepo.findByNo(emp);
		long tot = 0;
		for (DayOffEntity dayOffEntity : day) {
			tot += dayOffEntity.getUseDays();
		}
		
		daysOffNumbersRepo.save(DaysOffNumbersEntity.builder()
				.dno(use.getDno()).useDays(tot).totalDays(15).no(emp).build());
	}
	
}

