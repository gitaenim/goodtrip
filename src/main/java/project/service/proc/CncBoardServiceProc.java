package project.service.proc;

import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import project.domain.DTO.BoardCNCDTO;
import project.domain.entity.BoardCNCEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.BoardCNCEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.CNCBoardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CncBoardServiceProc implements CNCBoardService {
	
	@Autowired
	BoardCNCEntityRepository CNCRepository;

	@Autowired
	EmployeesEntityRepository employeesRepository;

	@Override
	public void findAllList(Model model) {
		List<BoardCNCEntity> list = CNCRepository.findAll();

		// false : 조회한 데이터가 있음
		// true : 조회한 데이터가 없음
		boolean nullcheck = false; // 조회한 데이터의 유무를 확인하는 변수

		if (list.isEmpty()) {
			nullcheck = true;
		}

		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("cncList", list);
	}
	
	@Override
	public void save(BoardCNCDTO cdto) {
			
		// 사번으로 사원정보 조회
		EmployeesEntity emp = employeesRepository.findById(cdto.getNo()).orElseThrow();

		// 경조사 테이블에 저장
		CNCRepository.save(cdto.toEntityForSave(emp));
	}
	@Override
	public void detail(long cncNo, Model model) {
		
		BoardCNCEntity entityData = CNCRepository.findById(cncNo).orElseThrow();
		//BoardCNCDTO date= new BoardCNCDTO(entityData);
		//BoardCNCDTO date=new BoardCNCDTO(1,"titleasdf","contentasdf");
				
		//model.addAttribute("cncDetail", date);
		model.addAttribute("cncDetail", entityData);
	}
	
	



}
