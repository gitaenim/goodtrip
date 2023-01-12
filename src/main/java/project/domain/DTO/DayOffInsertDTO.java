package project.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate; //휴가시작일
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate EndDate; //휴가종료일
	
	private long useDays; //휴가일수
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate draftDate; //기안일
	
	private long employeeNo; //사원번호
	
	public DayOffEntity toDayOffEntity(DaysOffNumbersEntity use, EmployeesEntity no) {
		long calc=use.getUseDays()+useDays;
		System.err.println(calc);
		use=use.builder().useDays(calc).build();
		return DayOffEntity.builder()
				.type(type).reason(reason).startDate(startDate).EndDate(EndDate).useDays(use).draftDate(draftDate).employeeNo(no)
				.build();
		
	}
	
}
