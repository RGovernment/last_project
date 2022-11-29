package team.last.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;
import team.last.project.entity.Option;
import team.last.project.service.MemberService;
import team.last.project.service.OptionService;

@Controller
@RequiredArgsConstructor
public class MyController {
	@Autowired
	private final OptionService optionService;

	@Autowired
	private final MemberService memberService;

	@RequestMapping("/kakaoError")
	public String logout(Model model) {
		model.addAttribute("kakaologinFailMsg", "비활성화된 계정입니다.");
		return "/kakaomsg";
	}

	@RequestMapping("/")
	public String root() {

		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String indexPage(@Nullable HttpSession session, Model model) {

		return "index";
	}

	@RequestMapping(value = "/errortest")
	public String erpage() {
		return "member/login";
	}

	@RequestMapping("/card")
	public String login() {

		return "review";
	}

	@RequestMapping("/er")
	public String erpage2() {

		return "er";
	}

	@RequestMapping("/terms")
	public String termsConditions() {
		return "terms";
	}

	@RequestMapping("/privateInfo")
	public String privateInfoConditions() {
		return "privateInfo";
	}

	@RequestMapping("/intro")
	public String introPage() {
		return "intro";
	}
	
	@GetMapping("/support")
	public String support() {
		return "support";
	}

	@PostMapping("/support")
	public String supportPage(@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email, Model model) {

		Member mem = memberService.memgetInfo(email);
		if (mem != null) {
			if (memberService.memberck(password, mem)) {
				if (mem.getSecession() == 1) {
					memberService.restore(email);
					model.addAttribute("Message", "회원정보가 복구되었습니다. 로그인 후 확인해주세요.");
				} else {
					model.addAttribute("Message", "탈퇴하지 않은 회원입니다. 다시 확인해주세요.");
				}
			} else {
				model.addAttribute("Message", "정보가 일치하지 않습니다. 다시 확인해주세요.");
			}
		} else {
			model.addAttribute("Message", "회원정보가 일치하지 않습니다. 다시 확인해주세요.");
		}
		return "support";
	}

	@RequestMapping("/price")
	public String price(Model model) {
		List<Option> optlist = optionService.optionList();
		model.addAttribute("optlist", optlist);
		return "price";
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