package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DepartmentsEntity;

public interface DepartmentsEntityRepository extends JpaRepository<DepartmentsEntity, Long> {

}
