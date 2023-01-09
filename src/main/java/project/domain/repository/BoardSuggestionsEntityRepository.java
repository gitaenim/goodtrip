package project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.BoardSuggestionsEntity;

@Repository
public interface BoardSuggestionsEntityRepository extends JpaRepository<BoardSuggestionsEntity, Long>{

}
