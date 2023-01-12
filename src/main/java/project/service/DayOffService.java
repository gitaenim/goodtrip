package project.service;

import project.domain.DTO.DayOffInsertDTO;

public interface DayOffService {

	void save(DayOffInsertDTO dto);

	void update(DayOffInsertDTO dto);


}