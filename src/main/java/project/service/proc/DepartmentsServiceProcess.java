package project.service.proc;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.DepartmentsDTO;
import project.domain.repository.DepartmentsEntityRepository;
import project.service.DepartmentsService;

@Service
public class DepartmentsServiceProcess implements DepartmentsService {
	/* 230116 한아 작성 */
	
	@Autowired
	DepartmentsEntityRepository DepartmentsRepo;
	
	//부서 정보 수정
	@Transactional
	@Override
	public void editDepartment(long no, DepartmentsDTO dto) {
		DepartmentsRepo.findById(no).map(entity->entity.updateDepartment(dto));
		
		
	}
	

}
