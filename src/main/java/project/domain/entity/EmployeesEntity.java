package project.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.enums.DepartmentRank;
import project.enums.MyRole;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
@Getter
//230104 안나 생성
public class EmployeesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long no;//사원번호
	
	@Column(nullable = false)
	private String name;//이름
	
	@Column(nullable = false)
	private String email;//이메일
	
	@Column(nullable = false)
	private String password;//비밀번호
	
	@Enumerated(EnumType.STRING)
	private DepartmentRank position;//직급
	
	@Column(nullable = true)
	private String phone;//연락처
	/* 230109 한아 수정 : 데이터 타입 변경 long -> String */
	
	@Column(name = "join_date")
	private LocalDate joinDate;//입사일
	
	@Column(name = "resign_date")
	private LocalDate resignDate;//퇴사일
	
	private String extension;//내선번호
	/* 230109 한아 수정 : 데이터 타입 변경 long -> String */
	
	private long salary;//급여
	
	@Column(name = "birth_date")
	private LocalDate birthDate;//생년월일
	
	@Column(name = "main_work")
	private String mainWork;//주 업무
	
	@Column(name = "delete_status", columnDefinition = "boolean default false")
	private boolean deleteStatus;//삭제여부
	
	@JoinColumn(name = "department_no")
	@ManyToOne
	private DepartmentsEntity departmentNo; //부서번호

	@Builder.Default
	@CollectionTable(name = "my_role")
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roles = new HashSet<>();

	//230109 한아 수정
	@Builder.Default
	@CollectionTable(name = "employees_position")
	@Enumerated(EnumType.STRING) //설정하지 않으면 숫자(ORDINAL)
	@ElementCollection(fetch = FetchType.EAGER) 
	private Set<DepartmentRank> positions = new HashSet<>();
	
	public EmployeesEntity addposition(DepartmentRank position) {
		positions.add(position);
		return this;
	}
	
	@JoinColumn(name = "image_no", nullable = true)
	@OneToOne
	private ImagesEntity imageNo; //이미지번호

	//role 적용
	public EmployeesEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	

}