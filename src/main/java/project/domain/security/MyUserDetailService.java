package project.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.repository.EmployeesEntityRepository;

public class MyUserDetailService {
	
	//DB 테이블에서 인증처리하기 위한 메서드
	@Autowired
	private EmployeesEntityRepository emRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyUserDetails(emRepo.findByEmail(username));
	}
	
	

}
