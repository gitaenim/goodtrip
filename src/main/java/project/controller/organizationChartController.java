package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@GetMapping("/ozc/groupList/{department}")
	public String groupListByDepartmentNo(Model model,@PathVariable Long department) {
		System.out.println("컨트롤러까지 왔나?");
		organizationChartService.findAllByDepartmentNo(model, department);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return "organizationChart/groupList/${department}";
	}
	//조직도 상세페이지
	@GetMapping("ozc/groupDetail")
    public String groupDetail() {
        return "organizationChart/groupDetail";
    }
}

