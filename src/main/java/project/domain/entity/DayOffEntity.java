package project.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "day_off")
@Entity
@Getter
//230104 안나 생성
public class DayOffEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_off_no", unique = true, nullable = false)
	private long dayOffNo; //휴가번호
	
	private String type; //휴가종류 
	
	private String reason; //휴가사유
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate; //휴가시작일
	
	@Column(name = "end_date", nullable = false)
	private LocalDate EndDate; //휴가종료일
	
	@Column(columnDefinition = "boolean default false")
	private boolean approval; //결재 승인여부
	
	@JoinColumn(name = "employee_no", nullable = false)
	@ManyToOne
	private EmployeesEntity employeeNo; //기안자:사원번호

	//230106 재근 생성
	public DayOffEntity employeeNo(EmployeesEntity employeeNo) {
		this.employeeNo = employeeNo;
		return this;
	}
	
	//230109 재근 생성
	@Column(name = "draft_date", updatable = false)
	@CreationTimestamp
	private LocalDate draftDate; //기안일
	
	//230110 재근 수정
	@JoinColumn(name = "dno", nullable = false)
	private long useDays; //휴가일수
	
	public DayOffEntity useDays(DaysOffNumbersEntity e) {
		this.useDays = e.getUseDays();
		return this;
	}
	
}

