package project.service.proc;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.EmployeesDetailUpdateDTO;
import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.entity.EmployeesEntity;
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
		
		List<EmployeesEntity> list1 = employeesRepo.findAllByDeleteStatusOrderByPositionRank(false);
		model.addAttribute("list1", list1);
		
		boolean nullcheck = false;
		if(list1.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);
	}
	
	//퇴직처리된 사원 조회
	@Override
	public void findAllByDeleteStatusTrue(Model model) {
		model.addAttribute("list1", employeesRepo.findAllByDeleteStatusOrderByPositionRank(true));
		
	}

	//organizationChart 부서별 리스트 조회
	@Override
	public void findAllByDepartmentNo(Model model, Long department) {
		System.out.println("department : "+department);
		
		List<EmployeesEntity> list2 = employeesRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderByPositionRank(department, false);
		model.addAttribute("list2", list2);
		
		boolean nullcheck = false;
		if(list2.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);
		
	}

	//조직도 디테일 페이지
	@Override
	public void findById(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailDTO::new)
				.orElseThrow());
	}
	//조직도 디테일 수정 페이지
	@Override
	public void findByIdEditMode(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailUpdateDTO::new)
				.orElseThrow());
	}
	//사원 정보 수정
	@Transactional
	@Override
	public void editmode(Long no, EmployeesUpdateDTO dto) {
		employeesRepo.findById(no).map(entity->entity.updateInfo(dto));
		
	}

	@Override
	public void findAllList(Model model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void treelist(Model model, Long no) {
		model.addAttribute("treelist", employeesRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderByPositionRank(no, false));

	}

	

	

	
}
