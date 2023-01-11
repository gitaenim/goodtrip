package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PinkBalloonApplication {
	/* 20230110 문대현 수정  */
	
	//프로젝트 작업시 EC2 환경에 따른 오류 안나게 하기 위해서 생성
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata","true");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PinkBalloonApplication.class, args);
	}

}
