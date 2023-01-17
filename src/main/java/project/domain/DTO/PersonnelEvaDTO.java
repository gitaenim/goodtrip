package project.domain.DTO;

import lombok.Data;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.PersonnelEvaEntity;

@Data
public class PersonnelEvaDTO {

	private long empNo;
	
	private int prepareScore; //업무 준비능력 점수
	
	private int performAbility; //독자적인 수행능력점수
	
	private int anotherAdility; //부수업무 수행력
	
	private int freeTime; //하루 여가시간
	
	private int foodAmount; //하루 식사량
	
	private int totalScore; //총점
	

	
	public PersonnelEvaEntity saveEntity(EmployeesEntity empNo) {
		return PersonnelEvaEntity.builder()
				.prepareScore(prepareScore).performAbility(performAbility)
				.anotherAdility(anotherAdility).freeTime(freeTime)
				.foodAmount(foodAmount).totalScore(totalScore).empNo(empNo)
				.build();
	}
	
	
	public PersonnelEvaEntity updateEntity(long num , EmployeesEntity empNo) {	
			return PersonnelEvaEntity.builder()
					.no(num).prepareScore(prepareScore).performAbility(performAbility)
					.anotherAdility(anotherAdility).freeTime(freeTime)
					.foodAmount(foodAmount).totalScore(totalScore).empNo(empNo)
					.build();
		
	
	}
	
	
}