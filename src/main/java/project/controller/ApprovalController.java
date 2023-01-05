package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprovalController {

	
	//휴가신청 리스트
	@GetMapping("/dayoff")
    public String noticeList() {
        return "approvalMgmt/dayOff";
    }
}
