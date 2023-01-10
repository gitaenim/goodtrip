package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import project.service.loginInfoService;

@Controller
public class logController {

	@Autowired
	loginInfoService service;
	
	//로그인 페이지
	@GetMapping("/login")
    public String login() {
        return "login/login";
    }
	
	@GetMapping("/login_success")
    public String loginSuccexx() {
        return "index";
    }
	
}
