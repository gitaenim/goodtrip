package project.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;
import project.domain.entity.EmployeesEntity;

@Data
//230105 재근 생성
public class DayOffInsertDTO {	
	//휴가 신청 DTO
	
	private String type; //휴가종류
	private String reason; //휴가사유
	private LocalDateTime startDate; //휴가시작일
	private LocalDateTime EndDate; //휴가종료일
	private DaysOffNumbersEntity useDays; //휴가일수
	private LocalDate draftDate; //기안일
	private EmployeesEntity employeeNo; //사원번호
	
	public DayOffEntity toDayOffEntity() {
		return DayOffEntity.builder()
				.type(type).reason(reason).startDate(startDate).EndDate(EndDate).useDays(useDays).draftDate(draftDate).employeeNo(employeeNo)
				.build();
	}
	
}
