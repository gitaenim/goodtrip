package project.domain.entity;

import java.time.LocalDateTime;
import java.util.Optional;

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
@Table(name = "daily_working_hours")
@Entity
@Getter
//230104 안나 생성
public class DailyWorkingHoursEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_no", unique = true, nullable = false)
	private long workNo; //근태번호
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime date; //날짜
	
	@Column(name = "clock_in")
	private String clockIn; //출근시간
	
	@Column(name = "clock_out")
	private String clockOut; //퇴근시간
	
	@JoinColumn(name = "employee_no")
	@ManyToOne
	private EmployeesEntity employeeNo; //사원번호


}
