package project.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.EmployeesEntity;

@Repository
public interface EmployeesEntityRepository extends JpaRepository<EmployeesEntity, Long>{


	EmployeesEntity findByEmail(String username);

	List<EmployeesEntity> findAllByDepartmentNoDepartmentNo(Long department);


}
