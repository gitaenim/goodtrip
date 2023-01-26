package project.service;

import java.util.List;

import org.springframework.ui.Model;

import project.domain.DTO.DayOffAppDTO;
import project.domain.DTO.DayOffInsertDTO;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DepartmentsEntity;
import project.enums.AuthorizeStatus;
import project.security.MyUserDetails;

public interface DayOffService {

	void save(DayOffInsertDTO dto);

	void update(DayOffInsertDTO dto);

	void personalDayOff(long no, Model model);

	void mydayoff(long no, Model model);

	void detail(long dayOffNo, Model model);

	void appList(DepartmentsEntity departmentNo, Model model);

	void delete(long dayOffNo);

	void findbyApproval(MyUserDetails myUserDetails, Model model);

	List<DayOffEntity> findbyApproval(AuthorizeStatus approval);

	//void approval(DayOffAppDTO dto, long dayOffNo);



}