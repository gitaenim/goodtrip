package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.BoardCNCEntity;

@Repository
public interface BoardCNCEntityRepository extends JpaRepository<BoardCNCEntity, Long>{

}
