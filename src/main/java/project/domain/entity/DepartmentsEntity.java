package project.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Entity
@Getter
//230104 안나 생성
public class DepartmentsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_no", unique = true, nullable = false)
	private long departmentNo; //부서번호
	
	@Column(name = "department_name", nullable = false)
	private String departmentName;//부서명
	
	@Column(name = "department_head", nullable = false)
	private String departmentHead; //부서장
	

}

