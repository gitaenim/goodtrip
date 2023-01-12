package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DailyWorkingHoursEntity;

public interface AttendanceRepository extends JpaRepository<DailyWorkingHoursEntity, Long>{

}
