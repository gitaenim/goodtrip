package project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import project.domain.DTO.BoardCNCDTO;
import project.domain.entity.BoardCNCEntity;

public interface CNCBoardService {

	void save(BoardCNCDTO cdto);

	void findAllList(Model model);

	void detail(long cncNo, Model model);

	List<BoardCNCEntity> findAll();

	void update(BoardCNCDTO cdto, long cncNo);

	void delete(long cncNo);

	void findListForIndex(ModelAndView modelAndView);


}