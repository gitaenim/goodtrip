package project.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DayOffEntity;
import project.domain.entity.EmployeesEntity;

public interface DayOffEntityRepository extends JpaRepository<DayOffEntity, Long>{

	List<DayOffEntity> findByEmployeeNo(EmployeesEntity emp);

}
