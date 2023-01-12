package project.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DailyWorkingHoursEntity;
import project.domain.entity.EmployeesEntity;

public interface AttendanceRepository extends JpaRepository<DailyWorkingHoursEntity, Long> {
	List<DailyWorkingHoursEntity> findByEmployeeNo(EmployeesEntity emp);

	Optional<DailyWorkingHoursEntity> findAllByClockInBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);

	List<DailyWorkingHoursEntity> findByEmployeeNoAndClockInBetween(EmployeesEntity emp, LocalDateTime startDatetime,
			LocalDateTime endDatetime);
}
