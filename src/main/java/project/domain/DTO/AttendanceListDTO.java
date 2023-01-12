package project.domain.DTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;
import project.domain.entity.DailyWorkingHoursEntity;

//230111 전체 근태리스트 뿌려주기용 DTO 안나작성
@Data
public class AttendanceListDTO {

	private long workNo; //근태번호
	
	private long employeeNo; //사원번호
	
	private LocalDate date;
	
	private LocalDateTime clockIn; //출근시간
	
	private LocalDateTime clockOut; //퇴근시간
	
	private String workingHour; //업무시간
	
	private String designatedWorkingHour; //지정 근무 시간
	
	private String status; //근무상태
	
	public AttendanceListDTO(DailyWorkingHoursEntity e) {
		this.workNo = e.getWorkNo();
		this.employeeNo = e.getEmployeeNo().getNo();
		this.date = LocalDate.from(e.getDate());
		this.clockIn = e.getClockIn();
		this.clockOut = e.getClockOut();
		this.designatedWorkingHour = "8:00";
		long workingHourS = Duration.between(clockIn, clockOut).toSeconds(); //근무시간 초단위로 계산
		if(workingHourS < 3600) {
			this.workingHour = "-0시간" + Long.toString(59-(workingHourS % 3600 / 60)) + "분" + Long.toString(60-(workingHourS % 60)) + "초";
		} else {
			this.workingHour = Long.toString(workingHourS / 3600 - 1) + "시간" + Long.toString(workingHourS % 3600 / 60) + "분" + Long.toString(workingHourS % 60) + "초";
		}
		
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