package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.DayOffInsertDTO;
import project.domain.entity.DayOffEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.DayOffEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.DayOffService;

@Service
public class DayOffServiceProcess implements DayOffService {

	@Autowired
	private DayOffEntityRepository dayOffRepo;
	
	@Autowired
	private EmployeesEntityRepository employeesRepo; 
	
	
	@Override
	public void save(DayOffInsertDTO dto, Long no) {
//		DayOffEntity entity=DayOffEntity.builder()
//				.type(dto.getType()).reason(dto.getReason()).startDate(dto.getStartDate()).EndDate(dto.getEndDate())
//				.employeeNo(EmployeesEntity.builder().no(employeesRepo.findById(no).orElseThrow()).build())
//				.build();
		DayOffEntity entity=DayOffEntity.builder()
				.type(dto.getType())
				.reason(dto.getReason())
				.startDate(dto.getStartDate())
				.EndDate(dto.getEndDate())
				.employeeNo(employeesRepo.findById(no).orElseThrow())
				.build();
				
		dayOffRepo.save(entity);
	}

}
