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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.domain.DTO.AttendanceRegClockInDTO;
import project.domain.DTO.AttendanceRegClockOutDTO;


@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "daily_working_hours")
@Entity
@Getter
//230104 안나 생성 230118 안나 수정 (clockIn수정, status추가)
public class DailyWorkingHoursEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_no", unique = true, nullable = false)
	private long workNo; //근태번호
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDate date; //날짜
	
	@CreationTimestamp
	@Column(name = "clock_in")
	private LocalDateTime clockIn; //출근시간
	
	@UpdateTimestamp
	@Column(name = "clock_out")
	private LocalDateTime clockOut; //퇴근시간
	
	@Column
	private String status;
	
	@JoinColumn(name = "employee_no")
	@ManyToOne
	private EmployeesEntity employee; //사원번호
	
	public DailyWorkingHoursEntity employee(EmployeesEntity employee) {
		this.employee = employee;
		return this;
	}
	
	//출근 업데이트
	public DailyWorkingHoursEntity update(AttendanceRegClockInDTO dto) {
		this.clockIn = dto.getClockIn();
		this.status = dto.getStatus();		
		return this;
	}
	
	//퇴근 업데이트
	public DailyWorkingHoursEntity update(AttendanceRegClockOutDTO dto) {
		this.clockOut = dto.getClockOut();
		this.status = dto.getStatus();
		return this;
	}




}