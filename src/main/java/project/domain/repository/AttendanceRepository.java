package project.domain.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DailyWorkingHoursEntity;
import project.domain.entity.EmployeesEntity;

public interface AttendanceRepository extends JpaRepository<DailyWorkingHoursEntity, Long> {
	List<DailyWorkingHoursEntity> findByEmployeeNo(EmployeesEntity emp);
	
	Page<DailyWorkingHoursEntity> findByEmployee_noOrderByDateDesc(long no, Pageable pageable);

	List<DailyWorkingHoursEntity> findAllByClockInBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);

	List<DailyWorkingHoursEntity> findByEmployeeNoAndClockInBetween(EmployeesEntity emp, LocalDateTime startDatetime,
			LocalDateTime endDatetime);
	

	Optional<DailyWorkingHoursEntity> findByClockInBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);

	Optional<DailyWorkingHoursEntity> findByClockInBetweenAndEmployeeNo(LocalDateTime startDatetime,
			LocalDateTime endDatetime, EmployeesEntity emp);

	Optional<DailyWorkingHoursEntity> findByEmployee_no(long no);

	Optional<DailyWorkingHoursEntity> findByEmployee_noAndClockInBetween(long no, LocalDateTime startDatetime,
			LocalDateTime endDatetime);

	List<DailyWorkingHoursEntity> findByClockInBetweenAndEmployee_no(LocalDateTime startDatetime,
			LocalDateTime endDatetime, long no);

	Page<DailyWorkingHoursEntity> findByEmployee_noAndDateBetweenOrderByDateDesc(long no, LocalDate dateStart,
			LocalDate dateEnd, Pageable pageable);

}
