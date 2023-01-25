package project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {
	
	
	EMPLOYEE("ROLE_EMPLOYEE"),
	PERSONALMANAGER("ROLE_PERSONAL"),
	NONE("ROLE_RESIGNED"),
	CEO("ROLE_CEO"); //230120 한아 추가
	
	private final String role;

}