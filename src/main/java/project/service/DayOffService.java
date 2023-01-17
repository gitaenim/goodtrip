package project.service;

import org.springframework.ui.Model;

import project.domain.DTO.DayOffInsertDTO;

public interface DayOffService {

	void save(DayOffInsertDTO dto);

	void update(DayOffInsertDTO dto);

	void personalDayOff(long no, Model model);

	void mydayoff(long no, Model model);

	void detail(long dayOffNo, Model model);

	void findByDepartmentNo(Long department, Model model);


}