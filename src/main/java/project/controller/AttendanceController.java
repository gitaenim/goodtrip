package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {
	
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
