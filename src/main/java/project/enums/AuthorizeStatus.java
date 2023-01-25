package project.enums;

import lombok.Getter;

@Getter
public enum AuthorizeStatus {
	
	UnderApproval("결재중"),
	FirstApproval("선결"),
	Approval("승인"),
	Return("반려");
	
	private final String label;
	
	AuthorizeStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }


}

