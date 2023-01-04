package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
	
	//신입 사원 등록 페이지
	@GetMapping("/Organization/regist")
    public String regist () {
        return "organization/regist.html";
    }
}
