package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.DTO.BoardCNCDTO;
import project.service.CNCBoardService;

@Controller
public class CNCBoardController {
	
	@Autowired
	CNCBoardService CNCservice;

	//경조사 리스트 페이지
	@GetMapping("/Board/cncList")
    public String cncList(Model model) {
		CNCservice.findAllList(model);
		return "Board/cncList";
	}
	
	//경조사 글쓰기 페이지
	@GetMapping("/Board/cncWrite")
    public String cncWrite() {
        return "Board/cncWrite";
    }

	//경조사 글쓰기 기능
	@PostMapping("/Board/cncWrite")
	public String CNCWriting(BoardCNCDTO cdto) {
		CNCservice.save(cdto);
		return "redirect:/Board/cncList";
	}

	//경조사 게시글에서 상세페이지로 이동
	@GetMapping("/cncDetail")
	public String CNCDetail(@RequestParam long cncNo, Model model) {
		CNCservice.detail(cncNo, model);
		return "Board/cncDetail";
	}
	
	//임시 경조사 조회 페이지!
	@GetMapping("/Board/cncDetail")
    public String CNCDetail() {
        return "Board/cncDetail";
    }
}