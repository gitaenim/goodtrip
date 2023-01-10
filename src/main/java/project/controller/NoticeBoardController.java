package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.DTO.BoardNoticeDTO;
import project.service.proc.NoticeBoardService;
import project.service.proc.NoticeBoardServiceProc;

@Controller
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeservice;
	
	
	//공지사항 게시판 리스트 이동
	@GetMapping("/Board/noticeList")
    public String noticeList() {
        return "Board/noticeList";
    }
	
	//공지사항 글쓰기 페이지 이동
	@GetMapping("/Board/noticeWrite")
    public String noticeWrite() {
        return "Board/noticeWrite";
        
    }
	
	//글쓰기 작성
	 @PostMapping("/Board/noticeWrite")   
	    public String noticeWriting(BoardNoticeDTO dto) {
		 noticeservice.save(dto);
	    	return "redirect:/Board/noticeWrite";
    }
	 

		
}
