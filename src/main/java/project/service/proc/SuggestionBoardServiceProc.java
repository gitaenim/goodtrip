package project.service.proc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.BoardSuggestionsDTO;
import project.domain.entity.BoardSuggestionsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.BoardSuggestionsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.SuggestionBoardService;

@Service
public class SuggestionBoardServiceProc implements SuggestionBoardService {

	@Autowired
	BoardSuggestionsEntityRepository suggestionsRepository;

	@Autowired
	EmployeesEntityRepository employeesRepository;

	// 건의사항 게시글 리스트페이지에 출력할 모든 데이터 조회 서비스
	@Override
	public void findAllList(Model model) {

		List<BoardSuggestionsEntity> list = suggestionsRepository.findAll();

		// false : 조회한 데이터가 있음
		// true : 조회한 데이터가 없음
		boolean nullcheck = false; // 조회한 데이터의 유무를 확인하는 변수

		if (list.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("suggestionList", list);
	}

	// 건의사항 DB에 데이터를 저장하는 서비스
	@Override
	public void save(BoardSuggestionsDTO dto) {

		// 사번으로 사원정보 조회
		EmployeesEntity emp = employeesRepository.findById(dto.getNo()).orElseThrow();

		// 건의사항 테이블에 저장
		suggestionsRepository.save(dto.toEntityForSave(emp));
	}
	
	// 건의사항 디테일 데이터 조회하는 서비스
	@Override
	public void detail(long suggestNo, Model model) {
		
		BoardSuggestionsEntity entityData = suggestionsRepository.findById(suggestNo).orElseThrow();
		
		model.addAttribute("suggestionDetail", entityData);
	}

}
