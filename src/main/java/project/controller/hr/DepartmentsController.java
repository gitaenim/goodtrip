package project.controller.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.DTO.DepartmentsDTO;
import project.domain.entity.DepartmentsEntity;
import project.domain.repository.DepartmentsEntityRepository;
import project.service.DepartmentsService;

@Controller
public class DepartmentsController {
	
	@Autowired
	private DepartmentsService departmentsService;
	
	@Autowired
	private DepartmentsEntityRepository departmentRepo;
	
	//부서관리페이지
	@GetMapping("/departments/manage")
	public String manageDepartments(Model model) {
		model.addAttribute("departmentList", departmentRepo.findAll());
		return "organizationChart/departmentManagement";
	}
	//부서등록
	@PostMapping("/departments/reg")
	public String registDepartments(Model model, String departmentName, String departmentHead) {
		departmentRepo.save(DepartmentsEntity.builder()
				.departmentName(departmentName)
				.departmentHead(departmentHead)
				.build());
		return "redirect:/departments/manage";
	}
	//수정삭제모드
	@GetMapping("/departments/edit")
	public String editDepartments(Model model) {
		model.addAttribute("departmentList", departmentRepo.findAll());
		return "organizationChart/departmentEditMode";
	}
	//수정완료
	@GetMapping("/departments/edit/{departmentNo}")
	public String editingDepartments(@PathVariable long departmentNo, DepartmentsDTO dto) {
		departmentsService.editDepartment(departmentNo, dto);
		return "redirect:/departments/manage";
	}
	//삭제
	@GetMapping("/departments/delete/{departmentNo}")
	public String deletingDepartments(Model model, @PathVariable long departmentNo) {
		departmentRepo.deleteById(departmentNo);
		return "redirect:/departments/edit";
	}
}
