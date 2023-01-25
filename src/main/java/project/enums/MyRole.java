package project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {
	
	EMPLOYEE("ROLE_EMPLOYEE"), //0
	PERSONALMANAGER("ROLE_PERSONAL"), //0
	NONE("ROLE_RESIGNED"), //
	CEO("ROLE_CEO");
	
	private final String role;

}