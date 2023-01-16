package project.service.proc;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import project.domain.DTO.EmployeesDetailDTO;
import project.domain.DTO.EmployeesInsertDTO;
import project.domain.entity.EmployeesEntity;
import project.domain.entity.ImagesEntity;
import project.domain.repository.EmployeesEntityRepository;
import project.domain.repository.ImagesEntityRepository;
import project.service.EmployeesService;
import project.utils.MyFileUtils;

@Service
public class EmployeesServiceProcess implements EmployeesService {

	/* 230106 한아 작성 */
	/* 230110 문대현 수정*/
	
	@Autowired
	private MyFileUtils utils;
	@Autowired
	private EmployeesEntityRepository employeesRepository;
	@Autowired
	private ImagesEntityRepository imgReposiroty;
	@Autowired
	private PasswordEncoder pe;

	private String tempPath = "employees/temp/";
	private String uploadPath = "employees/upload/";

	// 직원 이미지 AWS S3 Employee/temp 업로드
	@Override
	public Map<String, String> fileTempUpload(MultipartFile gimg) {

		return utils.store(tempPath, gimg);
	}

	// 직원 등록, 이미지 정보 등록, S3 내에 temp->실제 upload위치로 이동
	@Override
	public void save(EmployeesInsertDTO dto) {
		
		//S3내 Temp 파일 upload파일로 복사후 Temp 파일에 있는 이미지는 삭제
		//위의 과정 처리 후 해당 파일의 url 주소 반환(외부 접근 가능)
		String url = utils.upload(uploadPath, dto.getImgkey(), dto.getNewName());
		
		//ImgesEntity에 저장할 데이터를 생성
		ImagesEntity imgs = dto.toImageEntity(uploadPath, url);
		imgReposiroty.save(imgs);
		employeesRepository.save(dto.toEntity(pe, imgs));

	}
	//캘린더에서 사용할 임직원 정보 조회 기능 
	//임직원 정보를 모두 검색한후 리스트 형식으로 반환
	@Override
	public List<EmployeesEntity> findAll() {
		List<EmployeesEntity> list = employeesRepository.findAll();
		return list;
	}
	
	//로그인한 사원정보 마이페이지에 조회 기능
	@Override
	public void findemployee(long no , Model model) {
		Optional<EmployeesEntity> list = employeesRepository.findById(no);
		List<EmployeesDetailDTO> dto = list.stream().map(EmployeesDetailDTO::new).collect(Collectors.toList());
		model.addAttribute("mypage",dto);
	}

}
