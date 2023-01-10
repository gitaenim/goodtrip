package project.service.proc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	
	//이미지 temp 폴더 지정
	@Value("${file.location.temp}")
	private String tempUpload; //파일 업로드 위치
	
	//이미지 upload 폴더 지정
	@Value("${file.location.upload}")
	private String upload;
	
	//직원 이미지 temp 업로드
	@Override
	public Map<String, String> fileTempUpload(MultipartFile gimg) {
		return MyFileUtils.fileUpload(gimg, tempUpload);
	}
	
	
	
	@Autowired
	private EmployeesEntityRepository employeesRepository;
	@Autowired
	private ImagesEntityRepository imgReposiroty;
	@Autowired
	private PasswordEncoder pe;

	//직원 등록, 이미지 정보 등록, temp->실제 upload위치로 이동
	@Override
	public void save(EmployeesInsertDTO dto) {
		//dto.toImagesEntity(upload).forEach(imgReposiroty::save);
		ImagesEntity imgs = imgReposiroty.save(dto.toImageEntity(upload));
		long imgNo = imgs.getImageNo();
		System.out.println("이미지번호 가져와지나?"+imgNo);
		employeesRepository.save(dto.toEntity(pe, imgNo));
		
	}
	/* 230109 한아 수정 : OneToOne으로 변경
	//직원 등록, 이미지 정보 등록, temp->실제 upload위치로 이동
	@Override
	public void save(EmployeesInsertDTO dto) {
		EmployeesEntity entity =  employeesRepository.save(dto.toEntity(pe));
		dto.toImagesEntity(entity, upload).forEach(imgReposiroty::save);;
		
	}
	*/
	
	

}
