package project.domain.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.LastModified;

import lombok.Data;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ImagesEntity;
import project.domain.repository.ImagesEntityRepository;
import project.enums.DepartmentRank;
import project.enums.MyRole;
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
	
	private MyRole edit_authority;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	//employee 저장
	public EmployeesEntity toEntity(PasswordEncoder pe, long imgNo) {
		//System.out.println(birthDate);
		System.out.println("직원 저장 속 이미지 번호 "+imgNo);
		edit_authority = departmentNo==3 ? MyRole.PERSONALMANAGER : MyRole.EMPLOYEE;
		
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
				.salary(salary)
				.imageNo(ImagesEntity.builder().imageNo(imgNo).build())
				.editAuthority(edit_authority)
				.build()
				.addposition(position)
				.addRole(edit_authority);
	}

	public ImagesEntity toImageEntity(String url) {
		
		//temp 폴더에서 상위폴더인 upload로 이동시킴
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		
		return ImagesEntity.builder()
				.url(url)
				.oldName(oldName)
				.newName(newName)
				.build();
		
		
	}

	
}
