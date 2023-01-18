package project.service.proc;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.EmployeesDetailUpdateDTO;
import project.domain.DTO.EmployeesUpdateDTO;
import project.domain.entity.DepartmentsEntity;
import project.domain.entity.EmployeesEntity;
import project.domain.repository.DepartmentsEntityRepository;
import project.domain.repository.EmployeesEntityRepository;
import project.service.OrganizationChartService;

@Service
public class OrganizationChartServiceProcess implements OrganizationChartService {
	/* 230113 한아 작성 */
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	@Autowired
	DepartmentsEntityRepository departmentsRepo;

	//근무중인 사원 조회(Default)
	@Override
	public void findAllByDeleteStatusFalse(Model model, Pageable pageable) {
		Page<EmployeesEntity> pageResult = employeesRepo.findAllByDeleteStatusOrderByPositionRank(false, pageable);
		
		model.addAttribute("list1",pageResult);
		
		model.addAttribute("pageNum", pageResult.getNumber()+1 ); // 현재 페이지 번호 0번부터 시작하기 때문에 +1
		model.addAttribute("pageSize", pageResult.getSize()); // 한 페이지의 게시글 수
		model.addAttribute("pageTotal", pageResult.getTotalPages()); // 총 페이지 수
		model.addAttribute("endPage", 10); // 페이징 10개까지 보여줄거야
	}
	
	//퇴직처리된 사원 조회
	@Override
	public void findAllByDeleteStatusTrue(Model model) {
		model.addAttribute("list1", employeesRepo.findAllByDeleteStatusOrderByPositionRank(true));
		
	}

	//organizationChart 부서별 리스트 조회
	@Override
	public void findAllByDepartmentNo(Model model, Long department) {
		System.out.println("department : "+department);
		
		List<EmployeesEntity> list2 = employeesRepo.findAllByDepartmentNoDepartmentNoAndDeleteStatusOrderByPositionRank(department, false);
		model.addAttribute("list2", list2);
		
		boolean nullcheck = false;
		if(list2.isEmpty()) {
			nullcheck = true;
		}
		model.addAttribute("nullcheck", nullcheck);
		
	}

	//조직도 디테일 페이지
	@Override
	public void findById(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailDTO::new)
				.orElseThrow());
	}
	//조직도 디테일 수정 페이지
	@Override
	public void findByIdEditMode(Model model, Long no) {
		model.addAttribute("list", employeesRepo.findById(no)
				.map(EmployeesDetailUpdateDTO::new)
				.orElseThrow());
	}
	//사원 정보 수정
	@Transactional
	@Override
	public void editmode(Long no, EmployeesUpdateDTO dto) {
		employeesRepo.findById(no).map(entity->entity.updateInfo(dto));
		
	}
	//familyTree 대표이사 정보
	@Override
	public void findCEO(Model model) {
		EmployeesEntity ceo = employeesRepo.findByPositionRank(0L);
		model.addAttribute("ceo", ceo);
		model.addAttribute("ceoImg", ceo.getImageNo().getUrl());
	}
	//eva
	@Override
	public void findAllList(Model model) {
		
	}
	//familyTree 부서장 정보
	@Override
	public void findDepartmentHead(Model model) {
		List<DepartmentsEntity> dent = departmentsRepo.findAll();
		List<EmployeesEntity> eent = employeesRepo.findAll();
		
		List<String> str = new ArrayList<>(); //사용 전에 초기화 꼬옥 시켜주자(자리 만들어주기)
		
		for(DepartmentsEntity d : dent) {//부서장 이름과 동일한 이름을 가진 사원 이미지url 가져오기
			for(EmployeesEntity e : eent) {
				if(d.getDepartmentHead().equals(e.getName())) {
					System.err.println(d.getDepartmentHead());
					str.add(e.getImageNo().getUrl());
				} else if(d.getDepartmentHead()=="미정"){
					str.add("http://project.goodtrip.s3.ap-northeast-2.amazonaws.com/employees/upload/%EC%8B%AC%EC%88%A0%EA%B0%9C%EA%B5%AC%EB%A6%AC_24c769fd-0e41-4a73-82c6-95356a735d2a.png");
				}
			}
		}
//		//System.err.println(str.toString());
		model.addAttribute("images", str);
	}


	
}
