package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DTO.PersonnelEvaDTO;
import project.domain.entity.PersonnelEvaEntity;
import project.service.OrganizationChartService;
import project.service.PersonnelEvaService;

@Controller
public class PersonnelEvaController {
	
	@Autowired
	OrganizationChartService organizationChartService; 
	
	@Autowired
	PersonnelEvaService personnelEvaService;
	
	// 인사관리 평가 메인리스트페이지
	@GetMapping("/personnelEvaList")
	public String personnelEvaMain(Model model) {
		organizationChartService.findAllList(model);
		return "personnel/persommelEvaList";
	}
	
	// 인사관리 평가 페이지
	@GetMapping("/personnelEva/{no}")
	public String personnelEva(Model model, @PathVariable Long no) {
		personnelEvaService.getNo(no, model);
		return "personnel/personnelEva";
	}

	
	@PostMapping("/personnelEva/save")
	@ResponseBody
	public String personnelEvaSave(@RequestBody PersonnelEvaDTO dto ) {
	
		
		/*Json gson = new Gson();
			JsonObject jsonObject = new JsonObject();
		
			int num =personnelEvaService.save(dto);
			
			if(num==1) {
				jsonObject.addProperty("msg", "SUCCESS");
		
			}else {
				jsonObject.addProperty("msg", "FAIL");
			}
			String result = gson.toJson(jsonObject);
			return result;*/
		
		int num =personnelEvaService.save(dto);
		
		if(num==1) 
			
			return"SUCCESS";
		else
			return"FAIL";
	}
	

}