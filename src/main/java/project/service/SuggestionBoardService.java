package project.service;

import org.springframework.ui.Model;

import project.domain.DTO.BoardSuggestionsDTO;

public interface SuggestionBoardService {

	void save(BoardSuggestionsDTO dto);

	void findAllList(Model model);

	void detail(long suggestNo, Model model);

}
