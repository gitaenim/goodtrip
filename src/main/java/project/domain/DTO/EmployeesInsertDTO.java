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
	
	
	/* 1차 시도
	//이미지 저장
	public List<ImagesEntity> toImagesEntity(String url) {
		imgs = new ArrayList<>();
		ImagesEntity ent = ImagesEntity.builder()
									.oldName(oldName)
									.newName(newName)
									.url(url)
									.build();
		imgs.add(ent); //이미지 저장
		
		imgNo = ent.getImageNo();
		System.out.println(imgNo);
			
		//temp 폴더에서 상위폴더인 upload로 이동시킴
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
		
	}
	*/
	
	/*
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
	*/
	
	
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	//employee 저장
	public EmployeesEntity toEntity(PasswordEncoder pe, long imgNo) {
		//System.out.println(birthDate);
		System.out.println("직원 저장 속 이미지 번호"+imgNo);
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
				.imageNo(ImagesEntity.builder().imageNo(imgNo).build())
				.build()
				.addposition(position);
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
