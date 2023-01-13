package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.ScheduleEntity;

@Repository
public interface ScheduleEntityRepository extends JpaRepository<ScheduleEntity, Long>{

}
