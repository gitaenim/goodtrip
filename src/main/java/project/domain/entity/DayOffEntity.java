package project.domain.entity;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
	private LocalDateTime startDate; //휴가시작일
	
	@Column(name = "end_date", nullable = false)
	private LocalDateTime EndDate; //휴가종료일
	
	@Column(columnDefinition = "boolean default false")
	private boolean approval; //결재 승인여부
	
	@JoinColumn(name = "employee_no")
	@ManyToOne
	private EmployeesEntity employeeNo; //사원번호

}

