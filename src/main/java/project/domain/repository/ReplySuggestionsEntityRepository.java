package project.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.entity.BoardSuggestionsEntity;
import project.domain.entity.ReplySuggestionsEntity;

@Repository
public interface ReplySuggestionsEntityRepository extends JpaRepository<ReplySuggestionsEntity,Long>{

	List<ReplySuggestionsEntity> findBySuggestNo(BoardSuggestionsEntity board);

}
