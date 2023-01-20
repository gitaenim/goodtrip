package project.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.security.MyUserDetails;
import project.service.AttendanceService;
import project.service.CNCBoardService;
import project.service.NoticeBoardService;
import project.service.ScheduleService;
import project.service.SuggestionBoardService;

import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class LogController {
	
	@Autowired
	private AttendanceService service;  
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private CNCBoardService cncBoardService;
	
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@Autowired
	private SuggestionBoardService suggestionBoardService;
	
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
		
		long empNo = myUserDetails.getNo();
		
		scheduleService.findAllByTodayForIndex(LocalDate.now(), model,empNo);
		
		scheduleService.findAllByTomorrowForIndex(LocalDate.now().plusDays(1), model,empNo);
		
		return "/index";
	}
	@GetMapping("/indexboard")
	public ModelAndView indexboard(String select, ModelAndView modelAndView) {
		if(select.equals("공지사항")) {
			noticeBoardService.findListForIndex(modelAndView);
		}else if(select.equals("경조사")) {
			cncBoardService.findListForIndex(modelAndView);
		}else if(select.equals("건의사항")) {
			suggestionBoardService.findListForIndex(modelAndView);
		}
		modelAndView.setViewName("/indexboard");
		return modelAndView;
	}
	
	
}
