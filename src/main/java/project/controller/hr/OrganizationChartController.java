package project.controller.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.repository.DepartmentsEntityRepository;
import project.service.OrganizationChartService;

@Controller
public class OrganizationChartController {
	
	/* 230106 한아 작성 */

	@Autowired
	OrganizationChartService organizationChartService; 
	
	@Autowired
	private DepartmentsEntityRepository departmentRepo;
	
	//조직도 리스트
	@GetMapping("/ozc/groupList")
    public String groupList(Model model) {
		model.addAttribute("department", departmentRepo.findAll());
		organizationChartService.findAllByDeleteStatusFalse(model);
        return "organizationChart/groupList";
    }
	//퇴직사원 리스트
	@GetMapping("/ozc/groupList/retirement")
	public String groupListDeleted(Model model) {
		organizationChartService.findAllByDeleteStatusTrue(model);
		return "organizationChart/groupListRetirement";
	}
	//부서별 조직도 리스트
	//@ResponseBody //페이지 이동(.html)하기 위해서는 이거 쓰면 안됨
	@GetMapping("/ozc/groupList/{department}")
	public String groupListByDepartmentNo(Model model, @PathVariable Long department) {
		System.out.println(" >>>>> here?");
		organizationChartService.findAllByDepartmentNo(model, department);
		model.addAttribute("department", departmentRepo.findAll());
		model.addAttribute("departmentNo", department);
		return "organizationChart/groupListByDepartment";
	}
	//조직도 상세페이지
	@GetMapping("/ozc/groupDetail/{no}")
    public String groupDetail(Model model, @PathVariable Long no) {
		organizationChartService.findById(model, no);
        return "organizationChart/groupDetail";
    }
	
	//사원 상세페이지 수정모드
	@GetMapping("/ozc/groupDetail/edit/{no}")
	public String groupDetailEdit(Model model, @PathVariable Long no) {
		organizationChartService.findByIdEditMode(model, no);
		model.addAttribute("department", departmentRepo.findAll());
		return "organizationChart/groupDetailEditMode";
	}
	//사원 상세페이지 수정완료
	@GetMapping("/ozc/groupDetail/edited/{no}")
	public String groupDetailEdited(@PathVariable Long no, EmployeesUpdateDTO dto) {
		organizationChartService.editmode(no, dto);
		return "redirect:/ozc/groupDetail/{no}";
	}
	//Family Tree
	@GetMapping("/ozc/familytree")
	public String familytree(Model model) {
		organizationChartService.findCEO(model); //대표이사정보
		model.addAttribute("department", departmentRepo.findAll()); //부서정보불러오기
		organizationChartService.findDepartmentHead(model);//부서장이미지가져오기
		return "organizationChart/familytree";
	}
}

