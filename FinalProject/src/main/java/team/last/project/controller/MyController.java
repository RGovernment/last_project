package team.last.project.controller;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.MemberDto;
import team.last.project.entity.Member;
import team.last.project.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MyController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}

	@RequestMapping(value="/errorDenied")
	public String showAccessDeniedPage() {

		return "login";
	}

	@RequestMapping(value = "/errortest")
	public String error() {
		return "login";
	}

	@GetMapping(value = "/login")
	public String loginForm(HttpServletRequest req) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
			return "login";
		} else {
			return "index";
		}

	}

	@RequestMapping("/card")
	public String login() {

		return "review";
	}

	@RequestMapping("/res")
	public String Reservation() {
		return "Reservation";
	}

	@RequestMapping(value = "/mypage")
	public String mypage(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "mypage";
	}

	@RequestMapping("/terms")
	public String termsConditions() {
		return "terms";
	}

	@RequestMapping("/intro")
	public String introPage() {
		return "intro";
	}

	@GetMapping("/signup")
	public String sign_up(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "signup";
	}

	@PostMapping(value = "/signup")
	public String join(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			memberService.join(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "signup";
		}
		return "redirect:/index";
	}

	@RequestMapping("/price")
	public String price() {

		return "price";
	}

	@RequestMapping("/wishlist")
	public String wish() {

		return "wishlist";
	}

	@RequestMapping("/QAlist")
	public String qaList() {
		return "QAlist";
	}

	@RequestMapping("/room")
	public String layout(@RequestParam(value = "roomType") String roomType) {
		String type = "ARoom";
		if (roomType.equals("A")) {
		} else if (roomType.equals("B")) {
			type = "BRoom";
		} else if (roomType.equals("C")) {
			type = "CRoom";
		} else if (roomType.equals("D")) {
			type = "DRoom";
		}
		return type;
	}

};