package project.domain.DTO;

import java.time.LocalDateTime;

import lombok.Data;
import project.domain.entity.DayOffEntity;
import project.domain.entity.EmployeesEntity;

@Data
public class DayOffInsertDTO {
	
	//휴가 신청 DTO	
	private String type; //휴가종류
	private String reason; //휴가사유
	private LocalDateTime startDate; //휴가시작일
	private LocalDateTime EndDate; //휴가종료일
	private EmployeesEntity employeeNo; //사원번호
	
	public DayOffEntity toDayOffEntity() {
		return DayOffEntity.builder()
				.type(type).reason(reason).startDate(startDate).EndDate(EndDate).employeeNo(employeeNo)
				.build();
	}
	
}
