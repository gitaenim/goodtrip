package project.service;

import project.domain.DTO.AttendanceRegDTO;
import project.security.MyUserDetails;

public interface AttendanceService {

	void saveAttIn(String timeData, long no, AttendanceRegDTO attendanceRegDTO);



}
