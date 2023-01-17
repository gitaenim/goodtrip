package project.service;

import org.springframework.ui.Model;

import project.domain.DTO.EmployeesUpdateDTO;

public interface OrganizationChartService {

	void findAllByDepartmentNo(Model model, Long department);

	void findById(Model model, Long no);

	void findAllByDeleteStatusFalse(Model model);

	void findAllByDeleteStatusTrue(Model model);

	void editmode(Long no, EmployeesUpdateDTO dto);

	void findAllList(Model model);
}
