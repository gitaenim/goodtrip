package project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	//이미지 파일 선택시 temp폴더에 저장됩니다
	@ResponseBody
	@PostMapping("/employees/temp-upload")
	public Map<String, String> tempUpload(MultipartFile gimg){
		//System.out.println(" >>>>> "+gimg.getOriginalFilename());
		return service.fileTempUpload(gimg);
	}
	
	//신입 사원 등록
	@PostMapping("/employees/reg")
	public String employeesReg(EmployeesInsertDTO dto) {
		System.out.println(dto);
		service.save(dto);
		return "organization/regist";
		
	}
	
	//신입 사원 등록 페이지
	@GetMapping("/Organization/regist")
	public String regist () {
		return "organization/regist.html";
	}
}
