package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.AttendanceRegDTO;
import project.security.MyUserDetails;
import project.service.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;  
	
	//출근데이터 전송
	@ResponseBody
	@PostMapping("/attendance/in/{timeData}")
	public String attendanceIn(@PathVariable String timeData, @AuthenticationPrincipal MyUserDetails myUserDetails, AttendanceRegDTO attendanceRegDTO) {
		//System.out.println("timeData: " + timeData);
		service.saveAttIn(timeData, myUserDetails.getNo(), attendanceRegDTO);
		return "index";
	}
	
	@GetMapping("/attendanceList")
	public String attendenceList() {
		return "AttendanceMgmt/attendanceList";
	}
	
	@GetMapping("/myAttendance")
	public String myAttendance() {
		return "AttendanceMgmt/myAttendance";
	}
	
	@GetMapping("/myWorkingDay")
	public String myWorkingDay() {
		return "AttendanceMgmt/myWorkingDay";
	}
	
	@GetMapping("/myDayOff")
	public String myDayOff() {
		return "AttendanceMgmt/myDayOff";
	}
	
	@GetMapping("/personalAttendance")
	public String personalAttendance() {
		return "AttendanceMgmt/personalAttendance";
	}
	
	@GetMapping("/personalWorkingDay")
	public String personalWorkingDay() {
		return "AttendanceMgmt/personalWorkingDay";
	}
	
	@GetMapping("/personalDayOff")
	public String personalDayOff() {
		return "AttendanceMgmt/personalDayOff";
	}

}
