package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import project.domain.DTO.DayOffInsertDTO;
import project.service.DayOffService;
import project.service.proc.DayOffServiceProcess;

@Controller
public class ApprovalController {
	
	@Autowired
	private DayOffService service;
	
	//휴가신청 리스트
	@GetMapping("/dayoff")
    public String noticeList() {
        return "approvalMgmt/dayOff";
    }

	
	//휴가신청 save
	@PostMapping("/dayoff/{no}")
	public String dayOff(DayOffInsertDTO dto,@PathVariable Long no) {
		service.save(dto, no);
		return "approvalMgmt/dayOff";
	}
	
	//휴가결재
	@GetMapping("/dayoffApp")
    public String dayOffApp() {
        return "approvalMgmt/dayOffApp";
    }
}

