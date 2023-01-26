package project.domain.repository;

import java.util.List;

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

	List<DayOffEntity> findAllByEmployeeNoDepartmentNoDepartmentNo(long dno);

	DayOffEntity findByDayOffNo(long dayOffNo);

	List<DayOffEntity> findByEmployeeNoOrEmployeeNo_DepartmentNoAndApproval(EmployeesEntity emp, DepartmentsEntity departmentNo,
			AuthorizeStatus approval);

	List<DayOffEntity> findByEmployeeNoOrApproval(EmployeesEntity emp, AuthorizeStatus firstapproval);

	List<DayOffEntity> findByApproval(AuthorizeStatus approval);

}
