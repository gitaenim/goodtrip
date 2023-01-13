package project.domain.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.EmployeesEntity;

@Repository
public interface EmployeesEntityRepository extends JpaRepository<EmployeesEntity, Long>{


	EmployeesEntity findByEmail(String username);

	EmployeesEntity findAllByNo(long employeeNo);

	List<EmployeesEntity> findAllByDepartmentNoDepartmentNo(Long department);

	List<EmployeesEntity> findAllByDepartmentNoDepartmentNoOrderBySalaryDesc(Long department);

	List<EmployeesEntity> findAllByDeleteStatus(boolean b);

	List<EmployeesEntity> findAllByDeleteStatusOrderByDepartmentNo(boolean b);



}
