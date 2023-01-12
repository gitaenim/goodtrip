package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import project.security.MyUserDetails;
import project.service.AttendanceService;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class LogController {
	
	@Autowired
	private AttendanceService service;  
	
	//로그인 페이지
	@GetMapping("/login")
    public String login() {
        return "login/login";
    }

	@GetMapping("/loginInfo/{info}")
	public void loginInfo(@PathVariable long info, Model model) {

	}
	// 메인페이지 출퇴근시간 뿌려주기용
	@GetMapping("/")
	public String home(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
		service.attenList(myUserDetails.getNo(),model);
		return "/index";
	}

	
	
}
