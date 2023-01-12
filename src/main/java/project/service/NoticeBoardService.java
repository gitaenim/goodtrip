package project.service;


import org.springframework.ui.Model;

import project.domain.DTO.BoardNoticeDTO;


public interface NoticeBoardService {

	void save(BoardNoticeDTO dto);
	
	void detail(long noticeNo, Model model);

	void findAll(Model model);
}
