package project.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.BoardCNCDTO;

@Service
public interface CNCBoardService {

	void save(BoardCNCDTO cdto);

	void findAllList(Model model);

	void detail(long cncNo, Model model);

	void delete(long cncNo);

	void updateProc(long cncNo, BoardCNCDTO cdto);



}
