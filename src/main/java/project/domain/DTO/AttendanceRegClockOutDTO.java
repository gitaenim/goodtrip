package project.domain.DTO;

import java.time.LocalDateTime;


import lombok.Data;
import project.domain.entity.DailyWorkingHoursEntity;

//퇴근 입력용 DTO 230111 안나
@Data
public class AttendanceRegClockOutDTO {
	
	private long workNo;//근태번호
	
	private LocalDateTime date; //날짜
	
	private LocalDateTime clockIn; //출근시간

	private LocalDateTime clockOut; //퇴근시간
	
	private long employeeNo; //사원번호
	
	public DailyWorkingHoursEntity clockOutToDailyWorkingHoursEntity() {
		return DailyWorkingHoursEntity.builder()
				.workNo(workNo)
				.date(date)
				.clockOut(clockOut)
				.build();
	}


}