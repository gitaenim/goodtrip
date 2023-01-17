package project.domain.DTO;

import lombok.Data;
import project.domain.entity.DepartmentsEntity;

@Data
public class DepartmentsDTO {
	/* 230116 한아 작성 */
	
	private String departmentName;//부서명
	private String departmentHead; //부서장
	
	
	public DepartmentsEntity toDepartment() {
		
		return DepartmentsEntity.builder()
				.departmentName(departmentName)
				.departmentHead(departmentHead)
				.build();
		
	}
	
	

}
