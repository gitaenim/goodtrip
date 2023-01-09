package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class logController {

	
	
	//로그인 페이지
	@GetMapping("/login/login")
    public String login() {
        return "login/login";
    }
	
	@GetMapping("/login_success")
    public String loginSuccexx() {
        return "index";
    }
		
	@GetMapping("/loginInfo/{info}")
	public void loginInfo(@PathVariable long info, Model model) {
	
		

	}
	
}
