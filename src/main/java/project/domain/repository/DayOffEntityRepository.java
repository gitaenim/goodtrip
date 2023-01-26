package project.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DayOffEntity;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.enums.AuthorizeStatus;

public interface DayOffEntityRepository extends JpaRepository<DayOffEntity, Long>{

	List<DayOffEntity> findByEmployeeNo(EmployeesEntity emp);

	DayOffEntity findAllByDayOffNo(long no);

	List<DayOffEntity> findAllByDayOffNo(EmployeesEntity emp);

	List<DayOffEntity> findAllByEmployeeNo(EmployeesEntity findByDepartmentNo);

	DayOffEntity findByDayOffNo(long dayOffNo);

	List<DayOffEntity> findAllByApproval(AuthorizeStatus firstapproval);

	Page<DayOffEntity> findByEmployeeNoNameContaining(String search, Pageable page);

	Page<DayOffEntity> findByApprovalContaining(String search, Pageable page);

	Page<DayOffEntity> findAllByEmployeeNoDepartmentNoDepartmentNo(long dno, Pageable page);

}
