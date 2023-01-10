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
		model.addAttribute("list1", employeesRepo.findAll(Sort.by(Direction.ASC,"departmentNo")));

		
	}

	//organizationChart 부서별 리스트 조회
	@Override
	public void findAllByDepartmentNo(Model model, Long department) {
		System.out.println("department : "+department);
		model.addAttribute("list2", employeesRepo.findAllByDepartmentNoDepartmentNo(department));
		System.out.println("여기까지 성공?");
		
	}

}
