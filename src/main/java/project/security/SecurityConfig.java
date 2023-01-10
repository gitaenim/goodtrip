package project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	//DB 인증정보 이용 인증처리하는 service 커스터마이징
	@Bean
	MyUserDetailService customUserDetailsService() {
		return new MyUserDetailService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(

				authorize -> authorize
					.antMatchers("/js/**", "/images/**", "/css/**", "/","/**")
					.permitAll() // 허용해야하는 url 위쪽에 추가해주시면 되요 지금 위에 빼고는 전부 로그인 해야되요
					.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.loginProcessingUrl("/login") // form action
					.usernameParameter("email")
					.passwordParameter("password")
					.defaultSuccessUrl("/login_success")
					.permitAll()
				)
				.csrf(t -> t.disable())
				.headers().frameOptions().sameOrigin()

		;
		return http.build();
	}
}