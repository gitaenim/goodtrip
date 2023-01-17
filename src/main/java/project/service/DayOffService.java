package project.service;

import org.springframework.ui.Model;

import project.domain.DTO.DayOffInsertDTO;
import project.domain.entity.DepartmentsEntity;

public interface DayOffService {

	void save(DayOffInsertDTO dto);

	void update(DayOffInsertDTO dto);

	void personalDayOff(long no, Model model);

	void mydayoff(long no, Model model);

	void detail(long dayOffNo, Model model);

	void appList(long department, Model model);

	void appList2(long no, Model model);


}