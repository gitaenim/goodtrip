package project.service.proc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.domain.DTO.EmployeesInsertDTO;
import project.domain.repository.EmployeesEntityRepository;
import project.enums.DepartmentRank;
import project.service.EmployeesService;
import project.utils.MyFileUtils;

@Service
public class EmployeesServiceProcess implements EmployeesService {
	/* 230106 한아 작성 */

	
	//이미지 temp 폴더 지정
	@Value("${file.location.temp}")
	private String tempUp;
	
	//이미지 upload 폴더 지정
	@Value("${file.location.upload}")
	private String upload;
	
	//직원 이미지 temp 업로드
	@Override
	public Map<String, String> fileTempUp(MultipartFile gimg) {
		return MyFileUtils.fileUpload(gimg, tempUp);
	}
	
	
	
	@Autowired
	private EmployeesEntityRepository repository;
	@Autowired
	private PasswordEncoder pe;

	//직원 등록
	@Override
	public void save(EmployeesInsertDTO dto) {
		repository.save(dto.toEntity(pe)
				.addposition(DepartmentRank.Staff));
		
	}

}
