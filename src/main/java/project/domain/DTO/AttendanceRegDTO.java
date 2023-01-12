package project.domain.DTO;

import java.time.LocalDateTime;


import lombok.Data;
import project.domain.entity.DailyWorkingHoursEntity;

@Data
public class AttendanceRegDTO {
	
	private long workNo;//근태번호
	
	private LocalDateTime date; //날짜
	
	private String clockIn;
	
	private String clockOut;
	
	private long employeeNo;

	public DailyWorkingHoursEntity toDailyWorkingHoursEntity() {
		return DailyWorkingHoursEntity.builder()
				.workNo(workNo)
				.date(date)
				.build();
	}


}