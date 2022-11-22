package team.last.project.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
public class MypageController {

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
	public String editinfo() {
		return "/mypage/editinfo";
	}

	@PostMapping(value = "/editinfo")
	public String infock(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "info", required = false) String info, Model model) {
		String text = "";
		if (memberService.memberck(password, member)) {
			model.addAttribute("memberDto", new MemberDto(member.getEmail(), member.getName(), password,
					member.getPhone(), member.getSecession(), member.getGender()));
			text = "/mypage/" + info;
		} else {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			text = "/mypage/editinfo";
		}
		return text;
	}

	@PostMapping(value = "/editpro")
	public String editpro(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/mypage/editpro";
		}
		try {
			memberService.edit(member, memberDto.getName(), memberDto.getPhone());
			model.addAttribute("secessionMsg", "정보가 변경되었습니다. 보안을 위해 로그아웃합니다.");
		} catch (IllegalStateException e) {
			model.addAttribute("Message", e.getMessage());
			return "/mypage/editpro";
		}
		return "/member/secession_msg";
	}

	@PostMapping(value = "/editpass")
	public String editpass(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/mypage/editpass";
		}
		try {
			memberService.editPass(member, memberDto.getPassword());
			model.addAttribute("secessionMsg", "비밀번호가 변경되었습니다. 보안을 위해 로그아웃합니다.");

		} catch (IllegalStateException e) {
			model.addAttribute("Message", e.getMessage());
			return "/mypage/editpass";
		}
		return "/member/secession_msg";
	}

	@RequestMapping(value = "/QAcontent")
	public String qainfo(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		String user_title = "예약 문의합니다";
		String user_text = "5인이서 미니룸A를 이용하려하는데 요금표에는 4인과 6인밖에 있지않아 문의 드립니다 5인일 경우 요금은 어떻게 되나요?";
		String answer = "귀하께서 문의해주신 5인의 경우 4인과 6인의 중간가격으로 제공하고있습니다, 6인 이하의 경우 4인으로 예약하신후 매장에 방문하셔서 추가요금을 지불하신다면 5인이용이 가능합니다.";
		model.addAttribute("user_title", user_title);
		model.addAttribute("user_text", user_text);
		model.addAttribute("text", answer);
		model.addAttribute("member", member);
		return "/mypage/QAcontent";
	}

};
