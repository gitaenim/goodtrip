package project.service.proc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.BoardNoticeDTO;
import project.domain.entity.BoardNoticeEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.BoardNoticeRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.NoticeBoardService;

@Service
public class NoticBoardServiceProc implements NoticeBoardService {
	@Autowired
	BoardNoticeRepository noticeRepository;

	@Autowired
	EmployeesEntityRepository employeesRepository;

	@Override
	public void save(BoardNoticeDTO dto) {

		EmployeesEntity em = employeesRepository.findById(dto.getNo()).orElseThrow();

		noticeRepository.save(dto.toEntityForSave(em));

	}

	@Override
	public void detail(long noticeNo, Model model) {

		BoardNoticeEntity entityData = noticeRepository.findById(noticeNo).orElseThrow();

		model.addAttribute("noticeDetail", entityData);

	}

	@Override
	public void findAll(Model model) {
		List<BoardNoticeEntity> list = noticeRepository.findAll();

		boolean nullcheck = false;

		if (list.isEmpty()) {
			nullcheck = true;
		}

		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("noticeList", list);

	}

}
