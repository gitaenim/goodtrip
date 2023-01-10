package project.service;

import org.springframework.ui.Model;

import project.domain.DTO.ReplySuggestionsDTO;

public interface ReplySuggestionService {

	void save(ReplySuggestionsDTO dto);

	void findAllList(long suggestNo, Model model);

}
