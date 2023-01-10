package project.domain.entity;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

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
	
	@Enumerated(EnumType.STRING)//1:N 즉시로딩
	private DepartmentRank position;//직급
	
	@Column(nullable = true)
	private long phone;//연락처
	
	@Column(name = "join_date")
	private LocalDateTime joinDate;//입사일
	
	@Column(name = "resign_date")
	private LocalDateTime resignDate;//퇴사일
	
	private long extension;//내선번호
	
	private long salary;//급여
	
	@Column(name = "birth_date")
	private LocalDateTime birthDate;//생년월일
	
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

	//role 적용
	public EmployeesEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	

}