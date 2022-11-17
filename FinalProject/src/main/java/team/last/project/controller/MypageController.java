package team.last.project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;

@Controller
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
public class MypageController {
	@RequestMapping(value = "")
	public String mypage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
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

	@RequestMapping(value="/QAlist")
	public String qaList(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/mypage/QAlist";
	}
	
	@RequestMapping("/acc_del")
	public String deluser() {
		return "/mypage/accDel";
	}
	
};