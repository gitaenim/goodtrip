package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {
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
