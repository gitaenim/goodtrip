package project.service.proc;

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
	
	
	@Override
	public void save(DayOffInsertDTO dto) {
		EmployeesEntity emp=employeesRepo.findById(dto.getEmployeeNo()).orElseThrow();
		
		DaysOffNumbersEntity daysOffNumbers=daysOffNumbersRepo.findByNo(emp);		
		
		if(daysOffNumbers==null){
		daysOffNumbers=daysOffNumbersRepo.save(DaysOffNumbersEntity.builder()
				 .no(emp).totalDays(15).useDays(dto.getUseDays()) .build()); }
		 
		
		/*
		 * DayOffEntity entity=DayOffEntity.builder() .type(dto.getType())
		 * .reason(dto.getReason()) .startDate(dto.getStartDate())
		 * .EndDate(dto.getEndDate())
		 * .employeeNo(employeesRepo.findById(no).orElseThrow()) .build();
		 */
				
		dayOffRepo.save(dto.toDayOffEntity(daysOffNumbers, emp));
	}

}
