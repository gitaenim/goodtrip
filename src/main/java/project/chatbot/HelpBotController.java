package project.chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelpBotController {

	@Autowired
	private KomoranService komoranService;
	
	@PostMapping("/hbot")
	public String message(String message,Model model) throws Exception {
		String answer = "안녕하세요";
		if(!message.equals("안녕")) {
			answer = komoranService.nlpAnalyze(message);
		}
		//Thread.sleep(1000);
		LocalDateTime today=LocalDateTime.now();
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("a H:mm");
		
		model.addAttribute("today", today.format(dateFormatter));
		model.addAttribute("time", today.format(timeFormatter));
		model.addAttribute("answer", answer);
		
		return "chatbot/bot-message";
	}
}
