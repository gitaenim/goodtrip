package project.controller.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.domain.DTO.EmployeesUpdateDTO;
import project.service.OrganizationChartService;

@Controller
public class OrganizationChartController {
	
	/* 230106 한아 작성 */

	@Autowired
	OrganizationChartService organizationChartService; 
	
	//조직도 리스트
	@GetMapping("/ozc/groupList")
    public String groupList(Model model) {
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
	//@ResponseBody 페이지 이동하기 위해서는 이거 쓰면 안됨
	@GetMapping("/ozc/groupList/{department}")
	public String groupListByDepartmentNo(Model model, @PathVariable Long department) {
		organizationChartService.findAllByDepartmentNo(model, department);
		System.out.println("model : "+model);
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
		organizationChartService.findById(model, no);
		return "organizationChart/groupDetailEditMode";
	}
	//사원 상세페이지 수정완료
	@GetMapping("/ozc/groupDetail/edited/{no}")
	public String groupDetailEdited(@PathVariable Long no, EmployeesUpdateDTO dto) {
		organizationChartService.editmode(no, dto);
		return "redirect:/ozc/groupDetail/{no}";
	}
	
}

