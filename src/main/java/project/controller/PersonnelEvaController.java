package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonnelEvaController {
	
	// 인사관리 평가 메인리스트페이지
		@GetMapping("/personnelEvaList")
		public String personnelEvaMain() {
			return "personnel/persommelEvaList";
	}
	
	// 인사관리 평가 페이지
	@GetMapping("/personnelEva")
	public String personnelEva() {
		return "personnel/personnelEva";
	}
}
