package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.DTO.BoardSuggestionsDTO;
import project.service.SuggestionBoardService;

@Controller
public class SuggestionBoardController {

	@Autowired
	SuggestionBoardService suggestionservice;

	// 건의 게시판 리스트
	@GetMapping("/Board/suggestionList")
	public String suggestionList(Model model) {
		suggestionservice.findAllList(model);
		return "Board/suggestionList";
	}

	// 건의사항 글쓰기 페이지 이동
	@GetMapping("/Board/suggestionWrite")
	public String suggestionWrite() {
		return "Board/suggestionWrite";
	}

	// 건의사항 글쓰기 기능
	@PostMapping("/Board/suggestionWrite")
	public String suggestionWriting(BoardSuggestionsDTO dto) {
		suggestionservice.save(dto);
		return "Board/suggestionList";
	}

	// 건의사항 게시글에서 제목이 눌렀을 경우 해당 게시글 상세 페이지로 이동
	@GetMapping("/suggestDetail")
	public String suggestDetail(@RequestParam long suggestNo, Model model) {
		suggestionservice.detail(suggestNo, model);
		return "Board/suggestionDetail";
	}

	// 임시 건의사항 조회 페이지

	@GetMapping("/Board/suggestionDetail")
	public String suggestionDetail() {
		return "Board/suggestionDetail";
	}

}
