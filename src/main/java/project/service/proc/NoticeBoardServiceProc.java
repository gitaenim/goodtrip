package project.service.proc;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.BoardNoticeDTO;
import project.domain.entity.BoardNoticeEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.BoardNoticeRepository;
import project.domain.repository.EmployeesRepository;

@Service
public class NoticeBoardServiceProc implements NoticeBoardService{
	
	@Autowired
	BoardNoticeRepository noticeRepository;
	
	@Autowired
	EmployeesRepository employeesRepository;
	

	@Override
	public void detial(long suggestNo, Model model) {
		List<BoardNoticeEntity> list = noticeRepository.findAll();

		// false : 조회한 데이터가 있음
		// true : 조회한 데이터가 없음
		boolean nullcheck = false; // 조회한 데이터의 유무를 확인하는 변수

		if (list.isEmpty()) {
			nullcheck = true;
		}

		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("suggestionList", list);
	}
			

	@Override
	public void save(BoardNoticeDTO dto) {
		//사번 서원정보 조회
		EmployeesEntity ee = employeesRepository.findById(dto.getNo()).orElseThrow();
		
		noticeRepository.save(dto.toEntityForSave(ee));
	}

	@Override
	public void findAllList(long suggestNo, Model model) {
		// TODO Auto-generated method stub
		
	}

	
}



