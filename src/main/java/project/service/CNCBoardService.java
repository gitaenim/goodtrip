package project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.BoardCNCDTO;
import project.domain.entity.BoardCNCEntity;

@Service
public interface CNCBoardService {

	void save(BoardCNCDTO cdto);

	void findAllList(Model model);

	void detail(long cncNo, Model model);

	List<BoardCNCEntity> findAll();



}
