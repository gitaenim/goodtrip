package project.service;

import org.springframework.ui.Model;

public interface OrganizationChartService {

	void findAllList(Model model);

	void findAllByDepartmentNo(Model model, Long department);

}
