package team.last.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public String root(){
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}

	@GetMapping(value = "/login")
	public String loginForm(HttpServletRequest req) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		return "login";
	}
	@RequestMapping(value = "/errortest")
	public String error() {
		return "login";
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
	public String mypage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member")Member member, Model model) {
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