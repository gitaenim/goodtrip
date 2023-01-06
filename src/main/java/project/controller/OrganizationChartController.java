package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrganizationChartController {

	//조직도 리스트
	@GetMapping("ozc/groupList")
    public String groupList() {
        return "organizationChart/groupList";
    }
	//임시입니다! 조직도 상세페이지
	@GetMapping("ozc/groupDetail")
    public String groupDetail() {
        return "organizationChart/groupDetail";
    }
}

