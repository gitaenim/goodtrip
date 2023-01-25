package project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.chatbot.Answer;
import project.chatbot.AnswerRepository;
import project.chatbot.ChatBotIntention;
import project.chatbot.ChatBotIntentionRepository;
import project.domain.repository.PersonnelEvaRepository;

@SpringBootTest
class PinkBalloonApplicationTests {

	@Autowired
	PersonnelEvaRepository personnelEvaRepository;
	
	//@Test
	void findByEmployeesEntity_No() {
		personnelEvaRepository.findByEmpNo(1);
	}
	
	
	//-----------------챗봇-----------------//
	@Autowired
	AnswerRepository answer;
	
	@Autowired
	ChatBotIntentionRepository intention;
	
	//@Test
	void 의도() {
		intention.save(ChatBotIntention.builder()
				.name("번호")
				.answer(answer.findById(1L).get())
				.build());
	}
	
	//@Test
	void 답변() {
		answer.save(Answer.builder()
				.content("문의한 사원의 전화번호입니다.")
				.build());
	}
	//--------------------------------------//
}
