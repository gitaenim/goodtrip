package project.service.proc;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.DayOffAppDTO;
import project.domain.DTO.DayOffInsertDTO;
import project.domain.DTO.DayOffListDTO;
import project.domain.DTO.DayOffListEmpDTO;
import project.domain.DTO.DayOffMyListDTO;
import project.domain.entity.BoardCNCEntity;
import project.domain.entity.BoardSuggestionsEntity;
import project.domain.entity.DayOffEntity;
import project.domain.entity.DaysOffNumbersEntity;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ReplySuggestionsEntity;
import project.domain.repository.DayOffEntityRepository;
import project.domain.repository.DaysOffNumbersEntityRepository;
import project.domain.repository.DepartmentsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.enums.AuthorizeStatus;
import project.service.DayOffService;

@Service
public class DayOffServiceProcess implements DayOffService {

	@Autowired
	private DayOffEntityRepository dayOffRepo;
	
	@Autowired
	private EmployeesEntityRepository employeesRepo; 
	
	@Autowired
	private DaysOffNumbersEntityRepository daysOffNumbersRepo;
	
	
	//휴가 등록
	@Override
	public void save(DayOffInsertDTO dto) {
		
		EmployeesEntity emp=employeesRepo.findById(dto.getEmployeeNo()).orElseThrow();
		
		DaysOffNumbersEntity numbers=daysOffNumbersRepo.findByNo(emp);		
		if(numbers==null) {
			numbers=daysOffNumbersRepo.save(DaysOffNumbersEntity.builder()
				 .no(emp).totalDays(15).useDays(dto.getUseDays()).build()); 
		}
		//신청시 휴가시작일이 이미 등록되있는 휴가날짜사이에 들어있는지 체크해서 없을경우 저장
		boolean check=true;		
		List<DayOffEntity> list = dayOffRepo.findByEmployeeNo(emp);		
		for (DayOffEntity dayOffEntity : list) {
			List<LocalDate> days = getDatesBetweenTwoDates(dayOffEntity.getStartDate(), dayOffEntity.getEndDate());
			for (LocalDate localDate : days) {
				if(localDate.isEqual(dto.getStartDate())) {
					check=false;
				}
			}
			if(dayOffEntity.getEndDate().isEqual(dto.getStartDate())) {
				check=false;
			}
		}
		if(check) {
			dayOffRepo.save(dto.toDayOffEntity(emp));
		}else {
			
		}
	}
	
	/**
	 * 두 날짜 사이의 모든 날짜를 받아오는 기능(시작날짜부터 종료일 전날까지 리스트로 받아짐)
	 * 
	 * @param startDate 시작일(LoclaDate 타입)
	 * @param endDate   종료일(LoclaDate 타입)
	 * @return 두 날짜 사이의 모든 날짜 리스트를 반환
	 */
	public List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		return startDate.datesUntil(endDate).collect(Collectors.toList());
	}
	
	//휴가 신청일수 업데이트
	@Override
	public void update(DayOffInsertDTO dto) {
		//findAllByEmployeeNo(dto.getEmployeeNo());
		EmployeesEntity emp = employeesRepo.findById(dto.getEmployeeNo()).orElseThrow();
		List<DayOffEntity> day = dayOffRepo.findByEmployeeNo(emp);
		DaysOffNumbersEntity use = daysOffNumbersRepo.findByNo(emp);
		long tot = 0;
		for (DayOffEntity dayOffEntity : day) {
			tot += dayOffEntity.getUseDays();
		}
		
		daysOffNumbersRepo.save(DaysOffNumbersEntity.builder()
				.dno(use.getDno()).useDays(tot).totalDays(15).no(emp).build());
	}

	//사원별 휴가 리스트
	@Override
	public void personalDayOff(long no, Model model) {
		//DayOffEntity detail = dayOffRepo.findAllByDayOffNo(no);
		//model.addAttribute("result", detail);
		model.addAttribute("dayOffListEmp", employeesRepo.findById(no)
				.stream().map(DayOffListEmpDTO::new).collect(Collectors.toList()));
		model.addAttribute("dayOffList", dayOffRepo.findByEmployeeNo(employeesRepo.findById(no).orElseThrow())
				.stream().map(DayOffListDTO::new).collect(Collectors.toList()));
	}

	//내 휴가 리스트
	@Override
	public void mydayoff(long no, Model model) {
		model.addAttribute("myDayOffList", dayOffRepo.findByEmployeeNo(employeesRepo.findById(no).orElseThrow())
				.stream().map(DayOffMyListDTO::new).collect(Collectors.toList()));		
	}

	//부서장 결재 디테일	
	@Override
	public void detail(long dayOffNo, Model model) {		
		DayOffEntity ent=dayOffRepo.findById(dayOffNo).orElseThrow();		
		model.addAttribute("dayOffDetail", ent);		
		model.addAttribute("detailEmp", ent.getEmployeeNo());
	}

	//부서장 결재리스트
	@Override
	public void appList(DepartmentsEntity departmentNo,int pageNum, String search, String searchType, Model model) {
		int pageSize = 10;

		Page<DayOffEntity> list = null;

		Pageable page = PageRequest.of(pageNum - 1, pageSize, Direction.DESC, "draftDate");
		
		
		
		if (search == null) {
			list = dayOffRepo.findAll(page);
		} else {
			if(searchType.equals("name")) {
				list = dayOffRepo.findByEmployeeNoName(search, page);
			}else if(searchType.equals("approval")) {
				list = dayOffRepo.findByApproval(search, page);
			}
		}

		// false : 조회한 데이터가 있음
		// true : 조회한 데이터가 없음
		boolean nullcheck = false; // 조회한 데이터의 유무를 확인하는 변수

		if (list.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);		
		
		long dno=departmentNo.getDepartmentNo();
		dayOffRepo.findAllByEmployeeNoDepartmentNoDepartmentNo(dno, page);
		model.addAttribute("appList", dayOffRepo.findAllByEmployeeNoDepartmentNoDepartmentNo(dno, page));
		
	}

	/*
	 * //결재 반려(삭제)
	 * 
	 * @Override public void delete(long dayOffNo) { DayOffEntity dayoff =
	 * dayOffRepo.findById(dayOffNo).orElseThrow(); dayOffRepo.deleteById(dayOffNo);
	 * }
	 */

	//대표 결재리스트
	@Override
	public void approvalList2(Model model) {
		List<DayOffEntity> appList = dayOffRepo.findAllByApproval(AuthorizeStatus.FirstApproval);
		List<DayOffEntity> appList2 = dayOffRepo.findAllByApproval(AuthorizeStatus.Approval);
		appList.addAll(appList2);
		model.addAttribute("appCEOList", appList);
	}

	//대표 결재 디테일
	@Override
	public void detail2(long dayOffNo, Model model) {
		DayOffEntity ent=dayOffRepo.findById(dayOffNo).orElseThrow();		
		model.addAttribute("dayOffDetail", ent);		
		model.addAttribute("detailEmp", ent.getEmployeeNo());		
	}

	//상태별 대표 결재리스트
	@Override
	public void findAllByAuthorizeStatus(Model model, String status) {
		System.out.println("status : "+status);
		
	}

	
}

