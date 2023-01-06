package project.service.proc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DTO.BoardNoticeDTO;
import project.domain.entity.BoardNoticeEntity;
import project.domain.repository.BoardNoticeRepository;

@Service
public class NoticeBoardServiceProc implements NoticeBoardService{
	
	@Autowired
	private BoardNoticeRepository noticeRepository;
	
	public void save(BoardNoticeDTO dto) {
		BoardNoticeEntity board=BoardNoticeEntity.builder()
				.suggestNo(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
				noticeRepository.save(board);
	}
}
