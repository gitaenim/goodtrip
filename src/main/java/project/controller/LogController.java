package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {

	//로그인 페이지
	@GetMapping("/login/login")
    public String login() {
        return "login/login";
    }
		
}
