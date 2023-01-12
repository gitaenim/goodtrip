package project.domain.DTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;
import project.domain.entity.DailyWorkingHoursEntity;

//230111 내 근태리스트 뿌려주기용 DTO 안나작성
@Data
public class AttendanceMyListDTO {
	
	private LocalDate date; //일자
	
	private LocalDateTime clockIn; //출근시간
	
	private LocalDateTime clockOut; //퇴근시간
	
	private String workingHour; //일 근무 시간
	
	private String designatedWorkingHour; //지정 근무 시간
	
	private String status; //근무상태
		
	public AttendanceMyListDTO(DailyWorkingHoursEntity e) {
		this.date = e.getDate().toLocalDate();
		this.clockIn = e.getClockIn();
		this.clockOut = e.getClockOut();
		this.designatedWorkingHour = "8:00";
		long workingHourH = Duration.between(clockIn, clockOut).toMinutes();
		this.workingHour = Long.toString(workingHourH / 60) + ":" + Long.toString(workingHourH % 60);
		//휴가 넣어야함
		if(clockIn==null) {
			this.status = "미출근";
		} else if(clockOut.equals(clockIn)){
			this.status = "근무중";
		} else {
			this.status = "근무종료";
		}
		
	}




}