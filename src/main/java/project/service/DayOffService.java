package project.service;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import project.domain.DTO.DayOffAppDTO;
import project.domain.DTO.DayOffInsertDTO;
import project.domain.entity.DepartmentsEntity;
import project.enums.AuthorizeStatus;

public interface DayOffService {

	void save(DayOffInsertDTO dto);

	void update(DayOffInsertDTO dto);

	void personalDayOff(long no, Model model);

	void mydayoff(long no, Model model);

	void detail(long dayOffNo, Model model);

	void appList(DepartmentsEntity departmentNo, int pageNum, String search, String searchType, Model model);

	//void delete(long dayOffNo);

	void approvalList2(Model model);

	void detail2(long dayOffNo, Model model);

	void findAllByAuthorizeStatus(Model model, String status);



}