package project.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import project.domain.DTO.EmployeesInsertDTO;

public interface EmployeesService {
	/* 230106 한아 작성 */

	Map<String, String> fileTempUpload(MultipartFile gimg);

	void save(EmployeesInsertDTO dto);

}
