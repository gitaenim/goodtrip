package project.domain.DTO;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.enums.DepartmentRank;

@Data
public class EmployeesInsertDTO {
	
	private String name;//이름
	private String email;//이메일
	private String password;//비밀번호
	private DepartmentRank position;//직급
	private long phone;//연락처
	private LocalDateTime joinDate;//입사일
	private long extension;//내선번호
	private long salary;//급여
	private LocalDateTime birthDate;//생년월일
	private String mainWork;//주 업무
	private DepartmentsEntity departmentNo; //부서번호
	
	
	public EmployeesEntity toEntity(PasswordEncoder pe) {
		return EmployeesEntity.builder()
				.name(name)
				.email(email)
				.password(pe.encode(password))
				//.positions(getPosition())
				.phone(phone)
				.joinDate(joinDate)
				.extension(extension)
				.salary(salary)
				.birthDate(birthDate)
				.mainWork(mainWork)
				.departmentNo(departmentNo)
				.build();
	}
	
	

}
