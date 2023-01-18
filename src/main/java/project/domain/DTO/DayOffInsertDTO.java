package project.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;
import project.domain.entity.EmployeesEntity;
import project.enums.AuthorizeStatus;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate draftDate; //기안일
	
	private long employeeNo; //사원번호
	
	private long useDays; //휴가일수
	
	private AuthorizeStatus approval; //승인여부
	
	public DayOffEntity toDayOffEntity(EmployeesEntity no) {
		
		return DayOffEntity.builder()
				.type(type)
				.reason(reason)
				.startDate(startDate)
				.EndDate(EndDate)
				.useDays(useDays)
				.draftDate(draftDate)
				.employeeNo(no)
				.approval(approval)
				.build()
				.addAuthorize(approval);		
	}
	
}
