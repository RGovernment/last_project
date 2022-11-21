package team.last.project.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.MemberDto;
import team.last.project.entity.Member;
import team.last.project.service.MemberService;

@Controller
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
public class MypageController {
	private final PasswordEncoder passwordEncoder;
	private final MemberService memberService;

	@RequestMapping(value = "")
	public String mypage(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/mypage/mymain";
	}

	@RequestMapping("/wishlist")
	public String wish(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/mypage/wishlist";
	}

	@RequestMapping(value = "/QAlist")
	public String qaList(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/mypage/QAlist";
	}

	@RequestMapping(value = "/QAwrite")
	public String qawrite(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/mypage/QAwrite";
	}

	@RequestMapping("/acc_del")
	public String deluser() {
		return "/mypage/accDel";
	}

	@GetMapping("/editinfo")
	public String editinfo(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {

		MemberDto memberDto = new MemberDto(member.getEmail(), member.getName(), member.getPassword(),
				member.getPhone(), member.getSecession(), member.getGender());

		model.addAttribute("memberDto", memberDto);
		return "/mypage/editinfo";
	}

	@PostMapping(value = "/editinfo")
	public String infock(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("에러");
			return "/mypage/editinfo";
		}
		try {
			member.modifyMember(memberDto.getName(), memberDto.getPhone(), memberDto.getGender());
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/mypage/editinfo";
		}
		return "/mypage";
	}

};