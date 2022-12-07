package team.last.project.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
import team.last.project.security.AnonymousDeniedHandler;
import team.last.project.security.AuthenticationEntryPointCustom;
import team.last.project.security.CustomInvalidSessionStrategy;
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
			.antMatchers("/review/imglist").permitAll()
			.antMatchers("/review/imgidlist").permitAll()
			.antMatchers("/kakao/**").hasAnyRole("USER","KUSER")
			.antMatchers("/review/reviewupdate").hasAnyRole("USER","KUSER")
			.antMatchers("/review/reviewwrite").hasAnyRole("USER","KUSER")
			.antMatchers("/res/room").hasAnyRole("USER","KUSER")
			.antMatchers("/member/signup").hasRole("ANONYMOUS")
			.antMatchers("/mypage/editinfo").hasRole("USER")
			.antMatchers("/mypage/editpass").hasRole("USER")
			.antMatchers("/mypage/editpro").hasRole("USER")
			.antMatchers("/mypage/**").hasAnyRole("USER","KUSER")
			.mvcMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll()
			.and()
			.exceptionHandling().accessDeniedHandler(DeniedHandler())
			.authenticationEntryPoint(entryPoint());
			
		http.formLogin()
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
        	//.invalidateHttpSession(true)
        	.and()
        	.oauth2Login().defaultSuccessUrl("/member/kakao")
        	.userInfoEndpoint().userService(kakaoOAuth2UserService);
		
			
		http.sessionManagement()
			.enableSessionUrlRewriting(true)
			.maximumSessions(1).expiredUrl("/sessionerror2")
			.sessionRegistry(sessionRegistry())
			.and()
			.invalidSessionUrl("/sessionerror")
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.invalidSessionStrategy(custominvalidSessionStrategy())
			.sessionAuthenticationErrorUrl("/sessionerror")
			.sessionFixation()
			.migrateSession();
			
		return http.build();
	}
	
	
	
	 @Bean
	    public SessionRegistry sessionRegistry() {
	        return new SessionRegistryImpl();
	    }// Register HttpSessionEventPublisher

	@Bean
	public InvalidSessionStrategy custominvalidSessionStrategy() {
		
		return new CustomInvalidSessionStrategy();
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
