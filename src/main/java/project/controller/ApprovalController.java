package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.DTO.DayOffInsertDTO;
import project.security.MyUserDetails;
import project.service.DayOffService;
import project.service.proc.DayOffServiceProcess;

@Controller
public class ApprovalController {
	
	@Autowired
	private DayOffService service;
	
	//휴가신청 페이지
	@GetMapping("/dayoff")
    public String dayoff() {
        return "approvalMgmt/dayOff";
    }

	/**
	 * @author 재근
	 * @param dto 휴가등록
	 * @return 나의휴가일지
	 * 등록 업데이트
	 */
	
	//휴가신청 save
	@PostMapping("/dayoff")
	public String dayOff(DayOffInsertDTO dto) {
		service.save(dto); //휴가등록
		service.update(dto); //휴가일수 업데이트
		return "redirect:/myDayOff";
	}
	
	//직원별 휴가리스트
	@GetMapping("/personalDayOff/{no}")
    public String personalDayOff(@PathVariable long no, Model model) {
		service.personalDayOff(no, model); //no :  day off no
        return "AttendanceMgmt/personalDayOff";
    }
	
	//내 휴가리스트
	@GetMapping("/myDayOff")
	public String myDayOff(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
		service.mydayoff(myUserDetails.getNo(), model);
	    return "AttendanceMgmt/myDayOff";
	}
	
	//내 결재 리스트
	@GetMapping("/approvalList/{department}")
    public String approvalList(@PathVariable long department, Model model) {
		service.appList(department, model);
        return "approvalMgmt/approvalList";
    }
	
	//내 결재 리스트
	@GetMapping("/approvalList")
    public String approvalList2(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
		long no = myUserDetails.getNo();
		service.appList2(no, model);
        return "approvalMgmt/approvalList";
    }
	
	//결재용 휴가 디테일
	@GetMapping("/dayoffApp")
	public String dayOffApp(@RequestParam long dayOffNo, Model model) {
		service.detail(dayOffNo, model); //no :  day off no
		return "approvalMgmt/dayOffApp";
  }

	
}

