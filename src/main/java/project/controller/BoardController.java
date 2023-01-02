package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//공지사항 게시판 리스트
	@GetMapping("/Board/notice-list")
    public String noticeList() {
        return "Board/notice-list";
    }
	
	//공지사항 글쓰기 페이지
	@GetMapping("/Board/notice-write")
    public String noticeWrite() {
        return "Board/notice-write";
    }
	
	//건의 게시판 리스트
	@GetMapping("/Board/suggestion-list")
    public String suggestionList() {
        return "Board/suggestion-list";
    }
	
	//건의사항 글쓰기 페이지
	@GetMapping("/Board/suggestion-write")
    public String suggestionWrite() {
        return "Board/suggestion-write";
    }
	//자유 게시판 리스트
	@GetMapping("/Board/free-list")
    public String freeList() {
        return "Board/free-list";
    }
	
	//자유게시판 글쓰기 페이지
	@GetMapping("/Board/free-write")
    public String freeWrite() {
        return "Board/free-write";
    }
	//잠시 여기 둡니다!! 마이페이지
	@GetMapping("/employeeMgmt/mypage")
    public String mypage() {
        return "employeeMgmt/mypage";
    }
}
