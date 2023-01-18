package project.service.proc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.PersonnelEvaDTO;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.PersonnelEvaEntity;
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
	@Transactional
	public int save(PersonnelEvaDTO dto ) {
	
		System.out.println("dto. => "+ dto.toString());
		
		EmployeesEntity emp = empRepo.findById(dto.getEmpNo()).orElseThrow();
				// 1. regist_no 로 개인 평가를 조회
		if(perRepo.findByEmpNo(dto.getEmpNo()).isEmpty()) {
			System.out.println("비어있음  "+ dto.getEmpNo());
			System.out.println("??? =? "+(perRepo.findByEmpNo(dto.getEmpNo())));
			perRepo.save(dto.saveEntity(emp));
		}else {
			System.out.println("비어있지 않 "+ dto.getNo());
			perRepo.save(dto.updateEntity(emp));
			
		
		}		

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