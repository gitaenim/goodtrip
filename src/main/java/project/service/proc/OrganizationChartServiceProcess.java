package project.service.proc;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.repository.EmployeesEntityRepository;
import project.service.OrganizationChartService;

@Service
public class OrganizationChartServiceProcess implements OrganizationChartService {

	/* 230113 한아 작성 */
	
	@Autowired
	EmployeesEntityRepository employeesRepo;

	//근무중인 사원 조회(Default)
	@Override
	public void findAllByDeleteStatusFalse(Model model) {
		model.addAttribute("list1", employeesRepo.findAllByDeleteStatusOrderByDepartmentNo(false));
	}
	
	//퇴직처리된 사원 조회
	@Override
	public void findAllByDeleteStatusTrue(Model model) {
		model.addAttribute("list1", employeesRepo.findAllByDeleteStatus(true));
		
	}

	//organizationChart 부서별 리스트 조회
	@Override
	public void findAllByDepartmentNo(Model model, Long department) {
		System.out.println("department : "+department);
		model.addAttribute("list2", employeesRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderBySalaryDesc(department, false));
		
	}

	//조직도 디테일 페이지
	@Override
	public void findById(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailDTO::new)
				.orElseThrow());
		
	}
	//사원 정보 수정
	@Transactional
	@Override
	public void editmode(Long no, EmployeesUpdateDTO dto) {
		employeesRepo.findById(no).map(entity->entity.updateInfo(dto));
		
	}

	

	

	
}
