package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.domain.DTO.BoardSuggestionsDTO;
import project.domain.DTO.ReplySuggestionsDTO;
import project.service.ReplySuggestionService;
import project.service.SuggestionBoardService;

@Controller
public class SuggestionBoardController {

	@Autowired
	SuggestionBoardService suggestionservice;

	@Autowired
	ReplySuggestionService replySuggestionService;

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
		return "redirect:/Board/suggestionList";
	}

	// 건의사항 게시글에서 제목이 눌렀을 경우 해당 게시글 상세 페이지로 이동
	@GetMapping("/suggestDetail")
	public String suggestDetail(@RequestParam long suggestNo, Model model) {

		// 게시글 정보를 가져오는 기능
		suggestionservice.detail(suggestNo, model);

		// 해당 게시글의 댓글정보를 가져오는 기능
		replySuggestionService.findAllList(suggestNo, model);

		return "Board/suggestionDetail";
	}

	///// 댓글관련 //////

	// 댓글 작성시 저장 후 해당 게시글의 디테일 페이지로 리다이렉트
	@PostMapping("/ReplySuggestionWrite")
	public String ReplySuggestionWrite(ReplySuggestionsDTO dto, RedirectAttributes redirect) {
		replySuggestionService.save(dto);
		redirect.addAttribute("suggestNo", dto.getSuggestNo());
		return "redirect:/suggestDetail";
	}

}
