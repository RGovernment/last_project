package team.last.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private final MemberService memberService;
	
	@RequestMapping(value = "")
	public String adminPage(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		List<Member> memberlist = memberService.memberList();
		model.addAttribute("memberlist", memberlist);
		model.addAttribute("member", member);
		return "/admin/admin";
	}

	@RequestMapping("/room")
	public String room(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/admin/room";
	}

	@RequestMapping("/QAinfo")
	public String qnaInfo(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("member", member);
		return "/admin/QAinfo";
	}

	@RequestMapping("/modiroom")
	public String layout(@RequestParam(value = "roomType") String roomType,Model model) {
		

		String admin = "";
		String type = "/admin";
		
		model.addAttribute("score",4);
		model.addAttribute("star","70%");
		if (roomType.equals("A")) {
			admin = type + "/ARoom";
		} else if (roomType.equals("B")) {
			admin = type + "/BRoom";
		} else if (roomType.equals("C")) {
			admin = type + "/CRoom";
		} else if (roomType.equals("D")) {
			admin = type + "/DRoom";
		}
		return admin;
	}
	
	
}
