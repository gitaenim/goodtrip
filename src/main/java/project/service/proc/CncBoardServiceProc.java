package project.service.proc;

import org.springframework.ui.Model;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import project.domain.DTO.BoardCNCDTO;
import project.domain.entity.BoardCNCEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.BoardCNCEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.CNCBoardService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CncBoardServiceProc implements CNCBoardService {
	
	@Autowired
	BoardCNCEntityRepository CNCRepository;

	@Autowired
	EmployeesEntityRepository employeesRepository;

	@Override
	public void findAllList(Model model) {
		List<BoardCNCEntity> list = CNCRepository.findAll();

		// false : 조회한 데이터가 있음
		// true : 조회한 데이터가 없음
		boolean nullcheck = false; // 조회한 데이터의 유무를 확인하는 변수

		if (list.isEmpty()) {
			nullcheck = true;
		}

		model.addAttribute("nullcheck", nullcheck);
		model.addAttribute("cncList", list);
	}
	
	@Override
	public void save(BoardCNCDTO cdto) {
			
		// 사번으로 사원정보 조회
		EmployeesEntity emp = employeesRepository.findById(cdto.getNo()).orElseThrow();

		// 경조사 테이블에 저장
		CNCRepository.save(cdto.toEntityForSave(emp));
	}
	@Override
	public void detail(long cncNo, Model model) {
		
		BoardCNCEntity entityData = CNCRepository.findById(cncNo).orElseThrow();
		//BoardCNCDTO date= new BoardCNCDTO(entityData);
		//BoardCNCDTO date=new BoardCNCDTO(1,"titleasdf","contentasdf");
				
		//model.addAttribute("cncDetail", date);
		model.addAttribute("cncDetail", entityData);
	}

	/* 230113 문대현 생성 */
	// 모든 경조사 데이터 조회 한 후에 반환해 주는 기능
	@Override
	public List<BoardCNCEntity> findAll() {
		return CNCRepository.findAll();
	}
	
	//게시글 수정
	@Override
	public void update(BoardCNCDTO cdto, long cncNo) {
		// 사번으로 사원정보 조회
		EmployeesEntity emp = employeesRepository.findById(cdto.getNo()).orElseThrow();
		
		// 업데이트 기능
		CNCRepository.save(cdto.toEntityForUpdate(cncNo, emp));
	}
	//게시글 삭제
	@Override
	public void delete(long cncNo) {
			// 게시글 번호로 게시글 정보 조회
			BoardCNCEntity suggestions = CNCRepository.findById(cncNo).orElseThrow();
			// 해당 게시글을 삭제
			CNCRepository.deleteById(cncNo);
		}

}
	
	
	
/*	//삭제처리!
	@Override
	public void delete(long cncNo) {
		CNCRepository.deleteById(cncNo);
	}
	
	//수정처리!
	@Transactional // map()에서 수정한정보가 적용되어 update 쿼리가 실행됨
	@Override
	public void updateProc(long cncNo, BoardCNCDTO cdto) {
		//수정처리 대상은 bno(pk), 수정할데이터 dto:제목,내용
				//1.대상의 존재여부
				Optional<BoardCNCEntity> result= CNCRepository.findById(cncNo);
				//2.존재하면 수정
				if(result.isPresent()) {
					BoardCNCEntity entity=result.get();
					//수정하기위한 편의메서드 아니면 setter메서드 이용하세요
					entity.updateProc(cdto);
					///원본-업데이트 반영
					CNCRepository.save(entity);//이미 존재하는 Pk이면 수정반영됩니다.
				}
			}*/
	
	



