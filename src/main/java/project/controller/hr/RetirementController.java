package project.controller.hr;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.domain.DTO.EmployeesDeleteDTO;
import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.repository.EmployeesEntityRepository;
import project.enums.MyRole;

@Controller
public class RetirementController {
	/* 230112 한아 작성 */
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	//퇴직처리
	@Transactional
	@GetMapping("/retirement/delete/{no}")
	public String roleManager(@PathVariable long no, EmployeesDeleteDTO dto) {
		employeesRepo.findById(no).get().addRole(MyRole.NONE);
		employeesRepo.findById(no).map(entity->entity.updateDeleteStatus(dto));
		System.out.println("퇴직처리완료");
		return "redirect:/ozc/groupDetail/{no}";
	}
	
	//퇴직처리취소
	@Transactional
	@GetMapping("/retirement/rollback/{no}")
	public String roleEmployee(@PathVariable long no, EmployeesUpdateDTO dto) {
		employeesRepo.findById(no).get().addRole(MyRole.EMPLOYEE);
		employeesRepo.findById(no).map(entity->entity.updateRollbackStatus(dto));
		System.out.println("퇴직처리취소");
		return "redirect:/ozc/groupDetail/{no}";
	}


}
