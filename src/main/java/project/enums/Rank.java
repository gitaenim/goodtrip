package project.enums;

import lombok.Getter;

@Getter
public enum Rank {
	
	DepartmentManager("부장"),
	DeputyGeneralManager("차장"),
	GeneralManager("실장"),
	Manager("과장"),
	Chief("계장"),
	AssistantManager("대리"),
	SeniorClerk("주임"),
	Staff("사원"),
	Intern("인턴");
	
	private final String label;
	
	Rank(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}
