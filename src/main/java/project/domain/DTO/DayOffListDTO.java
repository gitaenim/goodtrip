package project.domain.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;

@Data
//230109 재근 생성
public class DayOffListDTO {
	//휴가 리스트 DTO
	
	private long dayOffNo; //휴가번호
	private String type; //휴가종류
	private LocalDateTime startDate; //휴가시작일
	private LocalDateTime EndDate; //휴가종료일
	private DaysOffNumbersEntity useDays; //휴가일수
	private LocalDate draftDate; //기안일
	
	public DayOffListDTO(DayOffEntity e) {
		this.dayOffNo = e.getDayOffNo();
		this.type = e.getType();
		this.startDate = e.getStartDate();
		EndDate = e.getEndDate();
		this.useDays = e.getUseDays();
		this.draftDate = e.getDraftDate();
	}
	
}
