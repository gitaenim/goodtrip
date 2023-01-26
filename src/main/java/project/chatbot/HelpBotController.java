package project.chatbot;

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
		model.addAttribute("msg", komoranService.nlpAnalyze(message));
		
		return "chatbot/bot-message";
	}
}
