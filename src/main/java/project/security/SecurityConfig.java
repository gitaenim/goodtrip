package project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(

				authorize -> authorize
						.antMatchers("/js/**", "/images/**", "/css/**", "/","/**")
						.permitAll() // 허용해야하는 url 위쪽에 추가해주시면 되요 지금 위에 빼고는 전부 로그인 해야되요

						.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/signin").loginProcessingUrl("/signin") // form action
						.usernameParameter("email").passwordParameter("pass").permitAll())
				.csrf(t -> t.disable())
				.headers().frameOptions().sameOrigin()

		;
		return http.build();
	}
}
