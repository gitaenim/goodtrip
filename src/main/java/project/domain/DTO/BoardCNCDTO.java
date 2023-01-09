package project.domain.DTO;

import lombok.Data;
import project.domain.entity.BoardCNCEntity;
import project.domain.entity.EmployeesEntity;

@Data
public class BoardCNCDTO {

	private long no;

	private String title;

	private String content;

	
	public BoardCNCEntity toEntityForSave(EmployeesEntity registNo) {
		return BoardCNCEntity.builder()
				.title(title).content(content).registNo(registNo)
				.build();
	}

	
	public BoardCNCEntity toEntityForUpdate(long cncNo, EmployeesEntity registNo) {
		return BoardCNCEntity.builder()
				.cncNo(cncNo).title(title).content(content).registNo(registNo)
				.build();
	}


	public BoardCNCDTO(BoardCNCEntity entityData) {
		no=entityData.getCncNo();
		title=entityData.getTitle();
		content=entityData.getContent();
	}


	public BoardCNCDTO(int no, String title, String content) {
		this.no=no;
		this.title=title;
		this.content=content;
	}
}
