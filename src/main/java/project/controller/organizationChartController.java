package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	//임시입니다! 조직도 상세페이지
	@GetMapping("ozc/groupDetail")
    public String groupDetail() {
        return "organizationChart/groupDetail";
    }
}

