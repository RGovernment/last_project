package team.last.project.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
//		//배포시 지워야할거 start
//		// IP, 세션 ID
//		WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
//		System.out.println("IP : " + web.getRemoteAddress());
//		System.out.println("Session ID : " + web.getSessionId());
//
//		// 인증 ID
//		System.out.println("name : " + authentication.getName());
//
//		// 권한 리스트
//		@SuppressWarnings("unchecked")
//		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
//		System.out.print("권한 : ");
//		for (int i = 0; i < authList.size(); i++) {
//			System.out.print(authList.get(i).getAuthority() + " ");
//		}
//		//지워야할거 end
		
		Date date = new Date(); 
		SimpleDateFormat dateform = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		String a = dateform.format(date);
		log.info("회원 이메일 : {}, 로그인 시각 : {}",authentication.getName(),a);
		
		HttpSession session = request.getSession();
		if (session != null) {
			String redirectUrl = (String) session.getAttribute("prevPage");
			if (redirectUrl != null) {
				session.removeAttribute("prevPage");
				getRedirectStrategy().sendRedirect(request, response, redirectUrl);
			} else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
