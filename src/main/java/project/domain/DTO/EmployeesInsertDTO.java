package project.domain.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.mvc.LastModified;

import lombok.Data;
import lombok.ToString;
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
	/* 20230110 문대현 수정 */
	
	private String name;//이름
	private String email;//이메일
	private String password;//비밀번호
	private DepartmentRank position;//직급
	private long positionRank;//직급순위
	private String phone;//연락처
	private String joinDate;//입사일
	private String resignDate;//퇴사일
	private String extension;//내선번호
	private long salary;//급여
	private String birthDate;//생년월일
	private String mainWork;//주 업무
	private long departmentNo; //부서번호
	
	private String oldName; //원본 이미지 파일이름
	private String newName;	//새로운 이미지 파일이름
	private String imgkey; //S3 내 임시 파일 경로
	
	private MyRole edit_authority;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	//employee 저장
	public EmployeesEntity toEntity(PasswordEncoder pe, ImagesEntity imgNo) {

		edit_authority = departmentNo==3 ? MyRole.PERSONALMANAGER : MyRole.EMPLOYEE;
		
		return EmployeesEntity.builder()
				.name(name)
				.email(email)
				.departmentNo(DepartmentsEntity.builder().departmentNo(departmentNo).build())
				.position(position)
				.positionRank(position.ordinal())
				.password(pe.encode(password))
				.birthDate(LocalDate.parse(birthDate, formatter))
				.mainWork(mainWork)
				.phone(phone)
				.extension(extension)
				.joinDate(LocalDate.parse(joinDate, formatter))
				.salary(salary)
				.imageNo(imgNo)
				.editAuthority(edit_authority)
				.build()
				.addposition(position)
				.addRole(edit_authority);
	}

	//이미지 데이터 저장 편의 메서드
	public ImagesEntity toImageEntity(String uploadPath, String url) {
		
		return ImagesEntity.builder()
				.url(url) //실제 데이터 경로(외부 적근 가능)
				.imgkey(uploadPath+newName) //S3내에 업로드한 이미지 파일 최종 위치
				.oldName(oldName) //원본 파일 이름
				.newName(newName) //새로운 파일 이름
				.build();
	}

	
}
