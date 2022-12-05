package team.last.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.MemberDto;
import team.last.project.entity.Member;
import team.last.project.service.EmailServiceImpl;
import team.last.project.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final EmailServiceImpl emailService;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping(value = "/login")
	public String loginForm(HttpServletRequest req) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
			return "member/login";
		} else {
			return "redirect:/index";
		}
	}
	
	@GetMapping("/kakao")
	public String kakao(Authentication au) {
		
		if(memberService.memgetInfo(au.getName()).getSecession() ==1) {
			return "redirect:/kakaoError";
		}
		return "redirect:/index"; 
	}
	
	@GetMapping("/secession")
	public String secession(Model model, Authentication authentication) {
		memberService.getbyEmail(authentication.getName());
		model.addAttribute("secessionMsg", "탈퇴가 완료되었습니다.");

		return "member/secession_msg";
	}

	
	@GetMapping("/signup")
	public String sign_up(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/signup";
	}

	@PostMapping(value = "/signup")
	public String join(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "member/signup";
		}
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			memberService.join(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/signup";
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/agree")
	public String agree() {
		
		return "/member/agree";
	}
	

	@RequestMapping(value = "/errorDenied")
	public String showAccessDeniedPage(Authentication authentication,HttpServletRequest request) {
		
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authList.get(0).getAuthority().contains("ADMIN")) {
			request.setAttribute("message", "어드민은 결제 및 마이페이지 기능을 사용할 수 없습니다.");
		}
		
		return "/er";
	}
	
	@ResponseBody
	@RequestMapping(value="/emailck")
	public String emailck(@RequestParam("email")String email) {
		String code = "";
		try {
			code = emailService.sendSimpleMessage(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
}