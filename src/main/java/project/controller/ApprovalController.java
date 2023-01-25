package project.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.domain.DTO.DayOffAppDTO;
import project.domain.DTO.DayOffInsertDTO;
import project.domain.repository.DayOffEntityRepository;
import project.enums.AuthorizeStatus;
import project.security.MyUserDetails;
import project.service.DayOffService;

@Controller
public class ApprovalController {
	
	@Autowired
	private DayOffService service;
	
	@Autowired
	DayOffEntityRepository dayOffRepo;
	
	//휴가신청 페이지
	@GetMapping("/dayoff")
    public String dayoff() {
        return "approvalMgmt/dayOff";
    }
	
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
	@GetMapping("/approvalList")
    public String approvalList(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model) {
		service.appList(myUserDetails.getDepartmentNo(), model);
        return "approvalMgmt/approvalList";
    }
	
	//직원별 휴가 디테일
	@GetMapping("/dayoffApp")
	public String dayOffApp(@RequestParam long dayOffNo, Model model) {
		service.detail(dayOffNo, model); //no :  day off no
		return "approvalMgmt/dayOffApp";
	}
	
	//부서장 결재승인
	@Transactional
	@GetMapping("/approval/{dayOffNo}")
	public String approval(@PathVariable long dayOffNo, DayOffAppDTO dto) {
		//System.out.println(dayOffNo);
		//dayOffRepo.findById(dayOffNo).get().addApproval(AuthorizeStatus.FirstApproval);
		dayOffRepo.findById(dayOffNo).map(t -> t.firstApproval(dto));
		return "redirect:/approvalList";
	}
	
	//대표 결재승인
	@Transactional
	@GetMapping("/approval2/{dayOffNo}")
	public String approval2(@PathVariable long dayOffNo, DayOffAppDTO dto) {
		dayOffRepo.findById(dayOffNo).map(t -> t.finalApproval(dto));
		return "redirect:/approvalList";
	}
	
	//결재 반려(삭제)
//	@PostMapping("/approvalDelete")
//	public String approvalDelete(long dayOffNo) {
//		service.delete(dayOffNo);
//		return "redirect:/approvalList";
//	}
	
	@PostMapping("/approvalDelete/{dayOffNo}")
	public String approvalDelete(@PathVariable long dayOffNo) {
		dayOffRepo.deleteById(dayOffNo);
		return "redirect:/approvalList";
	}
	
}

