package project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.domain.DTO.EmployeesInsertDTO;
import project.service.EmployeesService;

@Controller
public class EmployeesController {
	/* 230106 한아 작성 */
	
	@Autowired
	private EmployeesService service;
	//EmployeesService service = new EmployeesServiceProcess();

	@ResponseBody
	@PostMapping("/employees/temp-up")
	public Map<String, String> tempUp(MultipartFile gimg){
		return service.fileTempUp(gimg);
	}
	
	@PostMapping("/employees/reg")
	public String employeesReg(EmployeesInsertDTO dto) {
		service.save(dto);
		return "organization/regist";
		
	}
	
}
