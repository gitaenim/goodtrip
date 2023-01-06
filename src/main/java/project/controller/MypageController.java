package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController{

	//마이페이지 
	@GetMapping("/employeeMgmt/mypage")
    public String mypage() {
        return "employeeMgmt/mypage";
    }
}
