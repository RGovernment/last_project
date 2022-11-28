package team.last.project.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
import team.last.project.security.AnonymousDeniedHandler;
import team.last.project.security.AuthenticationEntryPointCustom;
import team.last.project.security.UserLoginFailHandler;
import team.last.project.security.UserLoginSuccessHandler;
import team.last.project.security.oauth2.KakaoOAuth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final KakaoOAuth2UserService kakaoOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.permitAll()
			.antMatchers("/res/room").authenticated()
			.antMatchers("/member/signup").hasRole("ANONYMOUS")
			.antMatchers("/mypage/editinfo").hasRole("USER")
			.antMatchers("/mypage/editpass").hasRole("USER")
			.antMatchers("/mypage/editpro").hasRole("USER")
			.antMatchers("/mypage/**").hasAnyRole("USER","KUSER")
			.mvcMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll()
			.and()
			.exceptionHandling().accessDeniedHandler(DeniedHandler())
			.authenticationEntryPoint(entryPoint())
			.and()
			.formLogin()
        	.loginPage("/member/login")
        	.usernameParameter("email")
        	.passwordParameter("password")
        	.defaultSuccessUrl("/")
        	.loginProcessingUrl("/member/login").permitAll()
        	.successHandler(successHandler())
        	.failureHandler(failureHandler())
        	.and()
        	.logout().permitAll()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/")
        	.invalidateHttpSession(true);
        	
        	http.oauth2Login().defaultSuccessUrl("/member/kakao")
        	.userInfoEndpoint().userService(kakaoOAuth2UserService);
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new UserLoginSuccessHandler();// default로 이동할 url
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new UserLoginFailHandler();// default로 이동할 url
	}

	@Bean
	public AccessDeniedHandler DeniedHandler() {
		return new AnonymousDeniedHandler();// default로 이동할 url
	}

	@Bean
	public AuthenticationEntryPoint entryPoint() {
		return new AuthenticationEntryPointCustom();
	}
}
