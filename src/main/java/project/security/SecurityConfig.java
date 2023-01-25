package project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	/* 20230109 문대현 수정 */
	/* 20230120 김한아 수정 */
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(

				authorize -> authorize
					.antMatchers("/js/**", "/image/**", "/css/**")
					.permitAll() // 허용해야하는 url
					//.hasRole("EMPLOYEE,PERSONALMANAGER")
					.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.loginProcessingUrl("/login") // form action
					.successHandler(new CustomLoginSuccessHandler()) //로그인 성공 핸들러 230120한아
					.usernameParameter("email")
					.passwordParameter("password")
					.permitAll()
				)
				.csrf(t -> t.disable())
				.headers().frameOptions().sameOrigin()

		;
		return http.build();
	}
}