package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.AttendanceRegClockInDTO;
import project.domain.DTO.AttendanceRegClockOutDTO;
import project.domain.DTO.AttendanceRegDTO;
import project.security.MyUserDetails;
import project.service.AttendanceService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService service;

	// 출근데이터 전송 230111 작성 안나
	@ResponseBody
	@PostMapping("/attendance/in")
	public String attendanceIn(@AuthenticationPrincipal MyUserDetails myUserDetails,
			AttendanceRegClockInDTO attendanceRegInDTO) {
		service.saveAttIn(myUserDetails.getNo(), attendanceRegInDTO);
		return "attinSuccess";
	}

	// 퇴근데이터 전송 230111 작성 안나 : logController
	@ResponseBody
	@PatchMapping("/attendance/out")
	public String attendanceOut(@AuthenticationPrincipal MyUserDetails myUserDetails,
			AttendanceRegClockOutDTO attendanceRegOutDTO) {
		service.saveAttOut(myUserDetails.getNo(), attendanceRegOutDTO);
		return "attoutSuccess";
	}
	// 230104 한아 작성
	// 근태 리스트

	// 전체 근태리스트 뿌려주기 230111 수정 안나
	@GetMapping("/attendanceList")
	public String attendenceList(Model model) {
		service.listAtt(model);
		return "AttendanceMgmt/attendanceList";
	}

	//내 근태+휴가 뿌려주기 230111 수정 안나 - 휴가 미설정
		@GetMapping("/myAttendance")
		public String myAttendance(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
			service.myListAtt(myUserDetails.getNo(), model);
			return "AttendanceMgmt/myAttendance";
		}

		//내 근태만 뿌려주기 230111 수정 안나
		@GetMapping("/myWorkingDay")
		public String myWorkingDay(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
			service.myListAttOnly(myUserDetails.getNo(), model);
			return "AttendanceMgmt/myWorkingDay";
		}
	
	/* 230113 재근 내 휴가 ApprovalController에 작업
	// 내 휴가
	@GetMapping("/myDayOff")
	public String myDayOff() {
			return "AttendanceMgmt/myDayOff";
	}
	*/

	//직원별 근태+휴가 뿌려주기 230111 수정 안나 - 휴가 미설정
		@GetMapping("/personalAttendance/{no}")
		public String personalAttendance(@PathVariable long no, Model model) {
			service.personalAtt(no, model);
			return "AttendanceMgmt/personalAttendance";
		}

		//직원별 근태 뿌려주기 230111 수정 안나
		@GetMapping("/personalWorkingDay/{no}")
		public String personalWorkingDay(@PathVariable long no, Model model) {
			service.personalWork(no, model);
			return "AttendanceMgmt/personalWorkingDay";
		}

	/* 230113 재근 직원별 휴가 ApprovalController에 작업
	// [김트립]님 휴가
	@GetMapping("/personalDayOff")
	public String personalDayOff() {
		return "AttendanceMgmt/personalDayOff";
	}
	*/

}
