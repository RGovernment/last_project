package team.last.project.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogOutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Date date = new Date();
		SimpleDateFormat dateform = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		String logdate = dateform.format(date);
		if (authentication != null) {
			log.info("회원 이메일 : {}, 로그아웃 시각 : {}", authentication.getName(), logdate);
		}
		String sessionInfo = request.getSession().getId();

		request.removeAttribute(sessionInfo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
