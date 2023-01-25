package project.service.proc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.DTO.PersonnelEvaDTO;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.PersonnelEvaEntity;
import project.domain.repository.DepartmentsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.domain.repository.PersonnelEvaRepository;
import project.service.PersonnelEvaService;

@Service
public class PersonnelServiceProc implements PersonnelEvaService {
	
	@Autowired
	PersonnelEvaRepository perRepo;
	
	@Autowired
	EmployeesEntityRepository empRepo;
	
	@Autowired
	private DepartmentsEntityRepository departmentRepo;
	
	//인사평가 저장
	@Override
	@Transactional
	public int save(PersonnelEvaDTO dto,EmployeesUpdateDTO empDto ) {
	
		System.out.println("dto. => "+ dto.toString());
		
		EmployeesEntity emp = empRepo.findById(dto.getEmpNo()).orElseThrow();
		EmployeesEntity empAdd = empRepo.findById(dto.getEmpNo()).get();		
		// 1. regist_no 로 개인 평가를 조회
		if(perRepo.findByEmpNo(dto.getEmpNo()).isEmpty()) {
			empAdd.setEmpGrade(dto.getEmpGrade());
			perRepo.save(dto.saveEntity(emp));
		}else {
			
			empAdd.setEmpGrade(dto.getEmpGrade());
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

	@Override
	public void findAllByOrderByNoDesc(Model model) {
		model.addAttribute("department", departmentRepo.findAll());
		List<EmployeesEntity> empList =  empRepo.findAllByOrderByNoDesc();
		List<EmployeesDetailDTO> dto = empList.stream().map(EmployeesDetailDTO::new).collect(Collectors.toList());
		System.out.println("TEST  1  => " + dto);
		System.out.println("TEST  2  => " + dto.toString());
		
		model.addAttribute("list", dto);
	}
	
	@Override
	public void findByEmpGrade(String empGrade) {
	}

};