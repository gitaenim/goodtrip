package project.service;


import org.springframework.ui.Model;

import project.domain.DTO.BoardNoticeDTO;


public interface NoticeBoardService {

	void save(BoardNoticeDTO dto);
	
	void findAllList(long suggestNo, Model model);

	void detial(long suggestNo, Model model);
}
