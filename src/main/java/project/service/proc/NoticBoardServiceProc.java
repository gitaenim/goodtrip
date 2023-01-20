package project.service.proc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

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

	// 공지사항 DB에 데이터를 저장하는 서비스
	@Override
	public void save(BoardNoticeDTO dto) {

		EmployeesEntity em = employeesRepository.findById(dto.getNo()).orElseThrow();

		noticeRepository.save(dto.toEntityForSave(em));

	}

	// 공지사항 디테일 페이지 조회하는 서비스
	@Override
	public void detail(long noticeNo, Model model) {

		BoardNoticeEntity entityData = noticeRepository.findById(noticeNo).orElseThrow();

		model.addAttribute("noticeDetail", entityData);

	}

	// 공지사항 게시글 리스트페이지에 출력할 모든 데이터 조회 서비스
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

	// 공지사항 게시글 삭제 기능
	@Override
	public void delete(long noticeno) {
		noticeRepository.deleteById(noticeno);

	}

	// 공지사항 게시글 내용 업데이트 기능
	@Override
	public void update(BoardNoticeDTO dto, long noticeNo) {
		// 사번으로 사원정보 조회
		EmployeesEntity emp = employeesRepository.findById(dto.getNo()).orElseThrow();

		// 업데이트 기능
		noticeRepository.save(dto.toEntityForUpdate(noticeNo, emp));
	}

	// 인덱스에 띄워줄 공지사항 게시글 내용
	@Override
	public void findListForIndex(ModelAndView modelAndView) {

		Pageable page = PageRequest.of(0, 5,Direction.DESC, "createDate");

		modelAndView.addObject("boardPage", noticeRepository.findAll(page));
		modelAndView.addObject("url", "/noticeDetail" );
		modelAndView.addObject("boardNo", "noticeNo" );

	}
}
