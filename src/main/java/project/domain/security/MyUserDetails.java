package project.domain.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import project.domain.entity.EmployeesEntity;

@Getter
public class MyUserDetails extends User{

	private long no;
	private String email;
	private String name;
	
	//extends User
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public MyUserDetails(EmployeesEntity entity) {
		this(entity.getEmail(), entity.getPassword(), entity.getRoles()//Set<MyRole>  --> Set<GrantedAuthority>
			.stream()
			.map(myRole->new SimpleGrantedAuthority(myRole.getRole()))
			.collect(Collectors.toSet()));
		this.no = entity.getNo();
		this.email = entity.getEmail();
		this.name = entity.getName();
	}
	
	
	
	

}
