package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.BoardNoticeEntity;

@Repository
public interface BoardNoticeRepository extends JpaRepository<BoardNoticeEntity, Long>{

}
