package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonnelController{
	
	@GetMapping("/personnelSchedule")
    public String mypage() {
        return "personnel/personnelSchedule";
    }
}
