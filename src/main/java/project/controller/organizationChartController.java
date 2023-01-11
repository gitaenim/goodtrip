package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.service.OrganizationChartService;

@Controller
public class organizationChartController {

	@Autowired
	OrganizationChartService organizationChartService; 
	
	//조직도 리스트
	@GetMapping("ozc/groupList")
    public String groupList(Model model) {
		organizationChartService.findAllList(model);
        return "organizationChart/groupList";
    }
	//부서별 조직도 리스트
	//@ResponseBody 페이지 이동하기 위해서는 이거 쓰면 안됨
	@GetMapping("/ozc/groupList/{department}")
	public String groupListByDepartmentNo(Model model, @PathVariable Long department) {
		organizationChartService.findAllByDepartmentNo(model, department);
		System.out.println("model : "+model);
		return "organizationChart/groupList2";
	}
	//조직도 상세페이지
	@GetMapping("/ozc/groupDetail/{no}")
    public String groupDetail(Model model, @PathVariable Long no) {
		System.out.println("here");
		organizationChartService.findById(model, no);
		//System.out.println("modelllllllllll : "+model);
        return "organizationChart/groupDetail";
    }
	
}

