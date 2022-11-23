package team.last.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.AskBoardDto;
import team.last.project.entity.AskBoard;
import team.last.project.entity.Member;
import team.last.project.service.AskBoardService;
import team.last.project.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private final MemberService memberService;
	@Autowired
	private AskBoardService askBoardService;
	
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

	@RequestMapping(value = "/QAlist")
	public String qaList(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<AskBoard> list = null;

		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		list = askBoardService.boardList(pageRequest);
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("member", member);
		return "/admin/QAlist";
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
	@GetMapping(value = "/QAanswer")
	public String qaanswer(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member, Integer id,
			Model model) {
		AskBoard askBoard = askBoardService.boardView(id);
		model.addAttribute("askBoard", askBoard);
		model.addAttribute("member", member);
		return "/admin/QAanswer";
	}
	@PostMapping(value = "/QAanswer/{id}")
	public String qaanswer(@PathVariable("id") Integer id, AskBoardDto askBoardDto, Model model) {
		askBoardService.boardUpdate(id, askBoardDto);
		model.addAttribute("message", "답변 전송 완료");
		model.addAttribute("Url", "/admin/QAlist");
		return "/admin/message";
	}
	
}
