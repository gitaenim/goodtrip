package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.service.OrganizationChartService;

@Controller
public class personnelEvaController {
	
	@Autowired
	OrganizationChartService organizationChartService; 
	
	// 인사관리 평가 메인리스트페이지
	@GetMapping("/personnelEvaList")
	public String personnelEvaMain(Model model) {
		organizationChartService.findAllList(model);
		return "personnel/persommelEvaList";
	}
	// 인사관리 평가 페이지
	@GetMapping("/personnelEva")
	public String personnelEva() {
		return "personnel/personnelEva";
	}


}
