package project.domain.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ImagesEntity;
import project.enums.DepartmentRank;
import project.utils.MyFileUtils;

@Data
public class EmployeesInsertDTO {
	/* 230109 한아 작성 */
	
	private String name;//이름
	private String email;//이메일
	private String password;//비밀번호
	private DepartmentRank position;//직급
	private String phone;//연락처
	private String joinDate;//입사일
	private String resignDate;//퇴사일
	private String extension;//내선번호
	private long salary;//급여
	private String birthDate;//생년월일
	private String mainWork;//주 업무
	private long departmentNo; //부서번호
	
	private String oldName;
	private String newName;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//employee 저장
	public EmployeesEntity toEntity(PasswordEncoder pe) {
		System.out.println(birthDate);
		return EmployeesEntity.builder()
				.name(name)
				.email(email)
				.departmentNo(DepartmentsEntity.builder().departmentNo(departmentNo).build())
				.position(position)
				.password(pe.encode(password))
				.birthDate(LocalDate.parse(birthDate, formatter))
				.mainWork(mainWork)
				.phone(phone)
				.extension(extension)
				.joinDate(LocalDate.parse(joinDate, formatter))
				.resignDate(LocalDate.parse(resignDate, formatter))
				.salary(salary)
				.build()
				.addposition(position);
	}
	
	//이미지 저장
	public List<ImagesEntity> toImagesEntity(EmployeesEntity employees, String url) {
		List<ImagesEntity> imgs = new ArrayList<>();
		ImagesEntity ent = ImagesEntity.builder()
									.url(url)
									.oldName(oldName)
									.newName(newName)
									.employeeNo(employees)
									.build();
		imgs.add(ent); //이미지 저장
			
		//temp 폴더에서 상위폴더인 upload로 이동시킴
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
		
	}
	
}
