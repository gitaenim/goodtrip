package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import project.domain.DTO.PersonnelEvaDTO;
import project.domain.repository.DepartmentsEntityRepository;
import project.service.OrganizationChartService;
import project.service.PersonnelEvaService;

@Controller
public class PersonnelEvaController {
	
	@Autowired
	OrganizationChartService organizationChartService; 
	
	@Autowired
	private DepartmentsEntityRepository departmentRepo;
	
	@Autowired
	PersonnelEvaService personnelEvaService;
	
	// 인사관리 평가 메인리스트페이지  //변경햇슴  1/17 수민
	@GetMapping("/personnelEvaList")
	public String personnelEvaMain(Model model) {
		model.addAttribute("department", departmentRepo.findAll());
		organizationChartService.findAllByDeleteStatusFalse(model);	
		return "personnel/persommelEvaList";
	}
	
	// 인사관리 평가 페이지
	@GetMapping("/personnelEva/{no}")
	public String personnelEva(Model model, @PathVariable Long no) {
		personnelEvaService.findById(no, model); //수정함 1/17 수민
		
		return "personnel/personnelEva";
	}
	
	
	@PostMapping("/personnelEva/save")
	@ResponseBody
	public String personnelEvaSave(@RequestBody PersonnelEvaDTO dto ) {
		
		
		
		int num = personnelEvaService.save(dto);
		Gson gson = new Gson();
		
		JsonObject jsonObject =new JsonObject();
		
		
		if(num==1) {
			jsonObject.addProperty("msg", "SUCCESS");
		}
		else {
			jsonObject.addProperty("msg", "FAIL");
		}
		String result = gson.toJson(jsonObject);
		
		return result;
	}
	

}