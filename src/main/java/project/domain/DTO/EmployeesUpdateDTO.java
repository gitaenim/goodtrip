package project.domain.DTO;

import lombok.Data;
import project.enums.MyRole;

@Data
public class EmployeesUpdateDTO {
	
	private MyRole edit_authority;

}

