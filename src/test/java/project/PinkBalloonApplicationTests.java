package project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.domain.DTO.BoardNoticeDTO;
import project.domain.entity.PersonnelEvaEntity;
import project.domain.repository.PersonnelEvaRepository;
import project.service.NoticeBoardService;
import project.service.PersonnelEvaService;

@SpringBootTest
class PinkBalloonApplicationTests {

	@Autowired
	PersonnelEvaRepository personnelEvaRepository;
	
	@Test
	void findByEmployeesEntity_No() {
		personnelEvaRepository.findByEmpNo(1);
	}
	
	
	
	@Test
	void contextLoads() {
	}
	
	
}
