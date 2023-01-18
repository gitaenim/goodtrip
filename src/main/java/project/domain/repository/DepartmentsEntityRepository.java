package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.DepartmentsEntity;

@Repository
public interface DepartmentsEntityRepository extends JpaRepository<DepartmentsEntity, Long>{
	/* 230116 한아 작성 */

}
