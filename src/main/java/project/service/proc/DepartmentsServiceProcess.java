package project.service.proc;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.DepartmentsDetailDTO;
import project.domain.DTO.DepartmentsUpdateDTO;
import project.domain.repository.DepartmentsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.DepartmentsService;

@Service
public class DepartmentsServiceProcess implements DepartmentsService {
	/* 230116 한아 작성 */
	
	@Autowired
	DepartmentsEntityRepository departmentsRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	//부서 정보 수정
	@Transactional
	@Override
	public void editDepartment(long no, DepartmentsUpdateDTO dto) {
		departmentsRepo.findById(no).map(entity->entity.updateDepartment(dto)); //부서명, 부서장 변경
	}
	//부서 정보 편집모드
	@Override
	public void departmentEditMode(Long departmentNo, Model model) {
		model.addAttribute("departmentInfo", departmentsRepo.findById(departmentNo)
				.map(DepartmentsDetailDTO::new).orElseThrow());
		model.addAttribute("employeesByDepartment", employeesRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderByPositionRank(departmentNo,false));
	}


}
