package project.domain.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.enums.DepartmentRank;

@Getter
@Setter
public class EmployeesDetailDTO {
	
	private String imgUrl; //이미지url
	private long no;//사원번호
	private String departmentName; //부서이름
	private String name;//이름
	private String email;//이메일
	private String position;//직급
	private String phone;//연락처
	private LocalDate joinDate;//입사일
	private String extension;//내선번호
	private LocalDate birthDate;//생년월일
	private String mainWork;//주 업무
	
	
	public EmployeesDetailDTO(EmployeesEntity ent) {
		this.imgUrl = ent.getImageNo().getUrl();
		this.no = ent.getNo();
		this.departmentName = ent.getDepartmentNo().getDepartmentName();
		this.name = ent.getName();
		this.email = ent.getEmail();
		this.position = ent.getPosition().label();
		this.phone = ent.getPhone();
		this.joinDate = ent.getJoinDate();
		this.extension = ent.getExtension();
		this.birthDate = ent.getBirthDate();
		this.mainWork = ent.getMainWork();
	}
	
	
	

}
