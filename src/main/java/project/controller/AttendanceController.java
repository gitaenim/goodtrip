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
	//230104 한아 작성
	//근태 리스트

	@GetMapping("/attendanceList")
	public String attendenceList() {
		return "AttendanceMgmt/attendanceList";
	}
	//내 근태 및 휴가
	@GetMapping("/myAttendance")
	public String myAttendance() {
		return "AttendanceMgmt/myAttendance";
	}
	//내 근무
	@GetMapping("/myWorkingDay")
	public String myWorkingDay() {
		return "AttendanceMgmt/myWorkingDay";
	}
	//내 휴가
	@GetMapping("/myDayOff")
	public String myDayOff() {
		return "AttendanceMgmt/myDayOff";
	}
	//[김트립]님 근태 및 휴가
	@GetMapping("/personalAttendance")
	public String personalAttendance() {
		return "AttendanceMgmt/personalAttendance";
	}
	//[김트립]님 근무
	@GetMapping("/personalWorkingDay")
	public String personalWorkingDay() {
		return "AttendanceMgmt/personalWorkingDay";
	}
	//[김트립]님 휴가
	@GetMapping("/personalDayOff")
	public String personalDayOff() {
		return "AttendanceMgmt/personalDayOff";
	}

}
