package team.last.project.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import team.last.project.constant.Role;
import team.last.project.security.AnonymousDeniedHandler;
import team.last.project.security.UserLoginFailHandler;
import team.last.project.security.UserLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.permitAll()
			.mvcMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/mypage").authenticated()
			.antMatchers("/QAlist").authenticated()
			.antMatchers("/wishlist").authenticated()
			.antMatchers("/**").permitAll();
		
		
		http.exceptionHandling().accessDeniedHandler(DeniedHandler());
		
		http.formLogin()
        	.loginPage("/login")
        	.usernameParameter("email")
        	.passwordParameter("password")
        	.defaultSuccessUrl("/")
        	.loginProcessingUrl("/login").permitAll()
        	.successHandler(successHandler())
        	.failureHandler(failureHandler())
        	.and()
        	.logout().permitAll()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/")
        	.invalidateHttpSession(true)
        	;
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	  return new UserLoginSuccessHandler();//default로 이동할 url
	}
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new UserLoginFailHandler();//default로 이동할 url
	}
	
	@Bean
	public AccessDeniedHandler DeniedHandler() {
		return new AnonymousDeniedHandler();//default로 이동할 url
	}
}
