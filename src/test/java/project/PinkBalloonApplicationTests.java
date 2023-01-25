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
	//@Test
	void 안녕의도() {
		intention.save(ChatBotIntention.builder()
				.name("안녕")
				.answer(answer.save(Answer.builder()
						.keyword("안녕")
						.content("안녕하세요</br>HELP BOT입니다.")
						.build()))
				.build());
		;
	}
	//@Test
	void 기타의도() {
		intention.save(ChatBotIntention.builder()
				.name("기타")
				.answer(answer.save(Answer.builder()
						.keyword("기타")
						.content("질문이 명확하지 않습니다</br>정확하게 입력 해 주세요!")
						.build()))
				.build());
		;
	}
	@Test
	void 휴가의도() {
		/*
		answer.save(Answer.builder()
				.keyword("휴가신청")
				.content("업무결재-휴가및병가 메뉴에서 신청할 수 있어요!")
				.build());
				*/
		intention.save(ChatBotIntention.builder()
				.name("신청")
				.upper(intention.save(ChatBotIntention.builder()
						.name("휴가")
						.build()))
				.answer(answer.findByKeyword("휴가신청").get())
				.build());
		
		
	}
	//--------------------------------------//
}
