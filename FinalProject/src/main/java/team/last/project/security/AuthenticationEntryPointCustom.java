package team.last.project.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class AuthenticationEntryPointCustom implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 if (authException instanceof InsufficientAuthenticationException) {
			 request.setAttribute("message", "로그인 후 이용해 주세요.");
			} else {
				 request.setAttribute("message", "세션이 만료되었습니다. 다시 로그인해주세요.");
			}
		 
		
		request.setAttribute("Url", "/member/login");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/er");
		dispatcher.forward(request, response);
	}

}
