package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.ScheduleInsertDTO;
import project.security.MyUserDetails;
import project.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	ScheduleService sceduleService;
	
	@ResponseBody
	@PostMapping("/scheduleAdd")
	public void scheduleAdd(ScheduleInsertDTO dto, @AuthenticationPrincipal MyUserDetails myUserDetails) {
		
		//로그인한 사원 정보
		long empNo = myUserDetails.getNo();
		
		sceduleService.save(dto,empNo);
		
	}
}
