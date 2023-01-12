package project.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;

@Data
//230109 재근 생성
public class DayOffListDTO {
	//휴가 리스트 DTO
	
	private long dayOffNo; //휴가번호
	
	private String type; //휴가종류
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime startDate; //휴가시작일
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime EndDate; //휴가종료일
	
	private DaysOffNumbersEntity useDays; //휴가일수
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate draftDate; //기안일
	
	/*
	 * public DayOffListDTO(DayOffEntity e) { this.dayOffNo = e.getDayOffNo();
	 * this.type = e.getType(); this.startDate = e.getStartDate(); this.EndDate =
	 * e.getEndDate(); this.useDays = e.getUseDays(); this.draftDate =
	 * e.getDraftDate(); }
	 */
	
}