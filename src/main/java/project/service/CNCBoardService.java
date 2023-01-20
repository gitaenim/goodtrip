package project.service;

import java.util.List;

import org.springframework.ui.Model;

import project.domain.DTO.BoardCNCDTO;
import project.domain.entity.BoardCNCEntity;

public interface CNCBoardService {

	void save(BoardCNCDTO cdto);

	void detail(long cncNo, Model model);

	List<BoardCNCEntity> findAll();

	void update(BoardCNCDTO cdto, long cncNo);

	void delete(long cncNo);

	void findAllList(int pageNum, String search, String searchType, Model model);


}