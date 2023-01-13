package project.service;

import java.util.List;

import project.domain.DTO.ScheduleInsertDTO;
import project.domain.entity.ScheduleEntity;

public interface ScheduleService {

	void save(ScheduleInsertDTO dto, long empNo);

	List<ScheduleEntity> findAll();

}
