package team.last.project.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AnonymousDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		request.setAttribute("message", "접근할 수 없는 페이지입니다.");
		request.setAttribute("Url", "/index");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/errorDenied");
		dispatcher.forward(request, response);
	}

}
