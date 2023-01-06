package project.service.proc;

import org.springframework.stereotype.Service;

import project.domain.DTO.BoardNoticeDTO;


public interface NoticeBoardService {

	void save(BoardNoticeDTO dto);
	
}
