package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.EmployeesEntity;

public interface EmployeesEntityRepository extends JpaRepository<EmployeesEntity, Long>{

}
