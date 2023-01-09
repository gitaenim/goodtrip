package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//공지사항 게시판 리스트
	@GetMapping("/Board/noticeList")
    public String noticeList() {
        return "Board/noticeList";
    } 
	
	//공지사항 글쓰기 페이지
	@GetMapping("/Board/noticeWrite")
    public String noticeWrite() {
        return "Board/noticeWrite";
    }
	
	//경조사 리스트 페이지
	@GetMapping("/Board/cncList")
    public String freeList() {
        return "Board/cncList";
    }
	
	//경조사 글쓰기 페이지
	@GetMapping("/Board/cncWrite")
    public String freeWrite() {
        return "Board/cncWrite";
    }
	
	//임시 경조사 조회 페이지!
	@GetMapping("/Board/cncDetail")
    public String freeDetail() {
        return "Board/cncDetail";
    }
	//임시 공지사항 조회 페이지!
	@GetMapping("/Board/noticeDetail")
    public String noticeDetail() {
        return "Board/noticeDetail";
    }
}
