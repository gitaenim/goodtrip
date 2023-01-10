package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.repository.EmployeesEntityRepository;
import project.service.OrganizationChartService;

@Service
public class OrganizationChartServiceProcess implements OrganizationChartService {

	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	//organizationChart 모든 리스트 조회
	@Override
	public void findAllList(Model model) {
		model.addAttribute("employeesList", employeesRepo.findAll(Sort.by(Direction.ASC,"departmentNo")));

		
	}

}
