package project.domain.DTO;

import lombok.Data;

@Data
public class AttendanceRegDTO {
	
	private long work_No;//근태번호
	
	private String name;
	
	private String department;
	
	private String position;
	
	
}