package project.service;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import project.domain.DTO.AttendanceRegClockInDTO;
import project.domain.DTO.AttendanceRegClockOutDTO;
import project.domain.DTO.AttendanceRegDTO;
import project.security.MyUserDetails;

public interface AttendanceService {

	void saveAttIn(long no, AttendanceRegClockInDTO attendanceRegInDTO);

	void attenList(long no, Model model);

	void saveAttOut(long no, AttendanceRegClockOutDTO attendanceRegOutDTO);

	void listAtt(Model model);

	void myListAtt(long no, Model model, Pageable pageable);

	void myListAttOnly(long no, Model model, Pageable pageable);

	void personalAtt(long no, Model model, Pageable pageable);

	void personalWork(long no, Model model, Pageable pageable);



}
