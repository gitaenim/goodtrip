package project.service;

import org.springframework.ui.Model;
import project.domain.DTO.PersonnelEvaDTO;

public interface PersonnelEvaService {
	
	int save(PersonnelEvaDTO dto);

	void findByEmpNo(long no, Model model);
	
	void getNo(long no, Model model);

	void findById(Long no, Model model);
	
	
}