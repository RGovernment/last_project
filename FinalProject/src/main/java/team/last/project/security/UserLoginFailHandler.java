package team.last.project.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class UserLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("message", "존재하지 않는 사용자입니다.");

		} else if (exception instanceof BadCredentialsException) {
			request.setAttribute("message", "아이디 또는 비밀번호가 틀립니다.");

		} else if (exception instanceof LockedException) {
			request.setAttribute("message", "잠긴 계정입니다.");

		} else if (exception instanceof DisabledException) {
			request.setAttribute("message", "비활성화된 계정입니다.");

		} else if (exception instanceof AccountExpiredException) {
			request.setAttribute("message", "만료된 계정입니다.");
			
		} else if (exception instanceof CredentialsExpiredException) {		
			request.setAttribute("message", "비밀번호가 만료되었습니다.");
			
		} else if (exception instanceof SessionAuthenticationException) {
			request.setAttribute("message", "이미 로그인된 디바이스가 있습니다.");
			
		}
		// 로그인 페이지로 다시 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/errortest");
		dispatcher.forward(request, response);
	}

}
