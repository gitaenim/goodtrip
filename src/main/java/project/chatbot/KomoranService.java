package project.chatbot;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.EmployeesEntityRepository;

@Service
public class KomoranService {
	
	
	@Autowired
	private Komoran komoran;
	
	
	public String nlpAnalyze(String message) {
		
		KomoranResult result=komoran.analyze(message);
		
		//문자에서 명사들만 추출한 목록(중복제거하기위해 set으로 저장)
		Set<String> nouns = result.getNouns().stream().collect(Collectors.toSet()); //set으로 저장하여 중복제거
		nouns.forEach((noun)->{
			System.out.println(">>>:"+ noun);
		});//메세지에서 명사추출
		
		String answer = "입력한 정보를 찾을 수 없습니다.";
		Answer ar = analyzeToken(nouns);
		if(ar!=null) {
			answer = ar.getContent();
			if(ar.getKeyword()!=null) answer += ", "+ar.getKeyword();
		}
		return answer;		
	}
	
	//입력된 목적어를 하나씩 파악하여 DB에서 검색하기위해 decisionTree()메서드로 전달
	private Answer analyzeToken(Set<String> nouns) {

		//1차 의도가 존재하는지 파악
		for(String token : nouns) {
			
			Optional<ChatBotIntention> result = decisionTree(token, null);
			if(result.isEmpty()) continue;
			System.out.print(">>>>> 1차 토큰 : "+token);

			Set<String> next = nouns.stream().collect(Collectors.toSet());
			next.remove(token);
			
			String keyword=null;
			if(token.contains("전화")) {
				PhoneInfo phone = analyzeTokenIsPhone(next);
				if(phone == null) {
					keyword = "입력한 사원을 찾을 수 없습니다.";
				} else {
					
					keyword = phone.getDeptName()+" : "+phone.getMemberName()+" : "+phone.getPhone();
				}
			}
			
			
			//2차분석 메서드
			return analyzeToken(next, result).keyword(keyword);
		}
		
		//분석 명사들이 등록한 의도와 일치하는게 존재하지 않을 경우 null
		return null;
	}
	
	@Autowired
	EmployeesEntityRepository member;
	
	//전화문의인 경우 DB에서 사원을 찾아서 처리
	private PhoneInfo analyzeTokenIsPhone(Set<String> next) {
		for(String name : next) {
			Optional<EmployeesEntity> m = member.findByName(name);
			if(m.isEmpty()) continue;
			//존재하면 해당부서명, 전화번호를 리턴
			String deptName = m.get().getDepartmentNo().getDepartmentName();
			String phone  = m.get().getPhone();
			String memberName  = m.get().getName();
			return PhoneInfo.builder()
						.deptName(deptName)
						.phone(phone)
						.memberName(memberName)
						.build();
		}
		return null;
		
	}

	//1차 의도가 존재하면
	//하위 의도가 존재하는지 파악
	private Answer analyzeToken(Set<String> next, Optional<ChatBotIntention> upper) {
		
		for(String token : next) {
			//1차 의도를 부모로 하는 토큰이 존재하는지 파악
			Optional<ChatBotIntention> result = decisionTree(token, upper.get());
			if(result.isEmpty()) continue;
			System.out.print(">>>>> 2차 토큰 : "+token);
			
			//1차-2차 의도 존재하는 경우
			return result.get().getAnswer(); //result2.get()해야 엔티티가 나옴
		}
		//1차만 존재하는 경우 답변
		return upper.get().getAnswer();
	}

	@Autowired
	ChatBotIntentionRepository intention;
	
	//의도가 존재하는지 DB에서 파악
	private Optional<ChatBotIntention> decisionTree(String token, ChatBotIntention upper) {
		return intention.findByNameAndUpper(token, upper);
	}

}
