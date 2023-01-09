package project.domain.entity;

import java.io.Serializable;
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
@Table(name = "days_off_numbers")
@Entity
@Getter
//230104 안나 생성
public class DaysOffNumbersEntity implements Serializable{
	
	@Id
	@JoinColumn(name = "no", unique = true, nullable = false)
	@ManyToOne
	private EmployeesEntity no; //사원번호
	
	@Column(name = "total_days")
	private String totalDays; //총 휴가일수
	
	@Column(name = "use_days")
	private String useDays; //사용일수
	
}