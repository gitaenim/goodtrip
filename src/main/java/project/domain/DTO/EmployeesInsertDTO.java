package project.domain.DTO;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ImagesEntity;
import project.domain.repository.EmployeesEntityRepository;
import project.enums.DepartmentRank;
import project.utils.MyFileUtils;

@Data
public class EmployeesInsertDTO {
	/* 230109 한아 작성 */
	
	private String name;//이름
	private String email;//이메일
	private String password;//비밀번호
	private DepartmentRank position;//직급
	private long phone;//연락처
	private LocalDateTime joinDate;//입사일
	private long extension;//내선번호
	private long salary;//급여
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime birthDate;//생년월일
	private String mainWork;//주 업무
	private long departmentNo; //부서번호
	
	private String oldName;
	private String newName;
	
	
	public EmployeesEntity toEntity(PasswordEncoder pe) {
		return EmployeesEntity.builder()
				.name(name)
				.email(email)
				.departmentNo(DepartmentsEntity.builder().departmentNo(departmentNo).build())
				.position(position)
				.password(pe.encode(password))
				.birthDate(birthDate)
				.mainWork(mainWork)
				.phone(phone)
				.extension(extension)
				.joinDate(joinDate)
				.salary(salary)
				.build()
				.addposition(position);
	}
	
	public void toImagesEntity(EmployeesEntity employees, String url) {
		ImagesEntity images = ImagesEntity.builder()
				.url(url)
				.oldName(oldName)
				.newName(newName)
				.employeeNo(employees)
				.build();
		
		//temp 폴더에서 상위폴더인 upload로 이동시킴
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		
	}
	
}
