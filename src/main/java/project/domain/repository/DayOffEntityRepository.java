package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.domain.entity.DayOffEntity;

public interface DayOffEntityRepository extends JpaRepository<DayOffEntity, Long>{

}
