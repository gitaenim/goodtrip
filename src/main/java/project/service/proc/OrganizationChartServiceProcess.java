package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.repository.EmployeesEntityRepository;
import project.service.OrganizationChartService;

@Service
public class OrganizationChartServiceProcess implements OrganizationChartService {

	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	//organizationChart 모든 리스트 조회
	@Override
	public void findAllList(Model model) {
		model.addAttribute("list1", employeesRepo.findAll(Sort.by(Direction.DESC,"departmentNo","salary")));

		
	}

	//organizationChart 부서별 리스트 조회
	@Override
	public void findAllByDepartmentNo(Model model, Long department) {
		System.out.println("department : "+department);
		//model.addAttribute("list2", employeesRepo.findAllByDepartmentNoDepartmentNo(department));
		model.addAttribute("list2", employeesRepo.findAllByDepartmentNoDepartmentNoOrderBySalaryDesc(department));
		
	}

	//조직도 디테일 페이지
	@Override
	public void findById(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailDTO::new)
				.orElseThrow());
		
	}

	
}
