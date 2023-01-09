package project.service.proc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.ReplySuggestionsDTO;
import project.domain.entity.BoardSuggestionsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ReplySuggestionsEntity;
import project.domain.repository.BoardSuggestionsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.domain.repository.ReplySuggestionsEntityRepository;
import project.service.ReplySuggestionService;

@Service
public class ReplySuggestionServiceProc implements ReplySuggestionService{

	@Autowired
	BoardSuggestionsEntityRepository boardSuggestionsRepository;
	
	@Autowired
	EmployeesEntityRepository employeesRepository;
	
	@Autowired
	ReplySuggestionsEntityRepository replySuggestionsRepository;
	
	
	// 댓글 저장 기능
	@Override
	public void save(ReplySuggestionsDTO dto) {
		
		// 댓글 작성자 정보 조회
		EmployeesEntity emp = employeesRepository.findById(dto.getRegistNo()).orElseThrow();
		
		// 작성될 게시글 정보 조회
		BoardSuggestionsEntity suggestion = boardSuggestionsRepository.findById(dto.getSuggestNo()).orElseThrow();
	
		replySuggestionsRepository.save(dto.toEntityForSave(emp, suggestion));
	}


	@Override
	public void findAllList(long suggestNo, Model model) {
		
		// 해당 게시글의 정보 조회
		BoardSuggestionsEntity board = boardSuggestionsRepository.findById(suggestNo).orElseThrow();
		
		// 조회된 게시글 정보를 통해서 관련된 모든 댓글 정보 리스트로 조회
		List<ReplySuggestionsEntity> list = replySuggestionsRepository.findBySuggestNo(board);
		
		// 댓글 내용이 있는 유무 체크하는 변수
		boolean nullcheck = false;
		
		if(list.isEmpty()) {
			nullcheck = true;
		}
		
		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("replyList", list);
	}

}
