package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.PersonnelEvaDTO;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.EmployeesEntityRepository;
import project.domain.repository.PersonnelEvaRepository;
import project.service.PersonnelEvaService;

@Service
public class PersonnelServiceProc implements PersonnelEvaService {
	
	@Autowired
	PersonnelEvaRepository perRepo;
	
	@Autowired
	EmployeesEntityRepository empRepo;
	
	//인사평가 저장
	@Override
	public int save(PersonnelEvaDTO dto ) {
		
		EmployeesEntity emp = empRepo.findById(dto.getEmpNo()).orElseThrow();
			System.out.println(perRepo.findById(dto.getEmpNo()));
			perRepo.save(dto.saveEntity(emp));
			/*	if(perRepo.findByEmpNo(dto.getEmpNo()).isEmpty()) {
					perRepo.save(dto.saveEntity(emp));
				}
				else {
					perRepo.deleteByEmpNo(dto.getEmpNo());
					perRepo.save(dto.saveEntity(emp));
				}
				*/
		
		return 1;
		
	}
	
	@Override
	public void findByEmpNo(long no, Model model) {
	
	}

	@Override
	public void getNo(long no, Model model) {
		
		model.addAttribute("no", no);
	}

	@Override //인사평가페이지 
	public void findById(Long no, Model model) {
		model.addAttribute("list", empRepo.findById(no)
				.map(EmployeesDetailDTO::new)
				.orElseThrow());
	}
	
	

};