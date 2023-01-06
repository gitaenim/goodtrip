package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//경조사는  CncBoardController 게시판 컨트롤러로 옮겼습니다  1/5 수민 
	
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
	
	//건의 게시판 리스트
	@GetMapping("/Board/suggestionList")
    public String suggestionList() {
        return "Board/suggestionList";
    }
	
	//건의사항 글쓰기 페이지
	@GetMapping("/Board/suggestionWrite")
    public String suggestionWrite() {
        return "Board/suggestionWrite";
    }
	
	//임시 공지사항 조회 페이지!
	@GetMapping("/Board/noticeDetail")
    public String noticeDetail() {
        return "Board/noticeDetail";
    }
	//임시 건의사항 조회 페이지!
	@GetMapping("/Board/suggestionDetail")
    public String suggestionDetail() {
        return "Board/suggestionDetail";
    }
}
