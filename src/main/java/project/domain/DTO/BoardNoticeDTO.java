package project.domain.DTO;

import lombok.Data;
import project.domain.entity.BoardNoticeEntity;
import project.domain.entity.EmployeesEntity;



@Data
public class BoardNoticeDTO {
	private long no; //사원번호
	
	private String title; //글 제목
	
	private String content;// 내용

	
	//글쓰고 저장
public BoardNoticeEntity toEntityForSave(EmployeesEntity registNo) {
	return BoardNoticeEntity.builder()
		.title(title).content(content).registNo(registNo)
		.build();
}
	}

