package team.last.project.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.AskBoardDto;
import team.last.project.dto.MemberDto;
import team.last.project.entity.AskBoard;
import team.last.project.entity.Member;
import team.last.project.service.AskBoardService;
import team.last.project.service.MemberService;

@Controller
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
public class MypageController {
	private final AskBoardService askBoardService;
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
			Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<AskBoard> list = null;

		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		list = askBoardService.boardUserList(member.getId(), pageRequest);
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		if(endPage==0) {
			endPage=1;
		}
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("member", member);
		return "/mypage/QAlist";
	}

	@GetMapping(value = "/QAwrite")
	public String qawriteform(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			Model model) {
		model.addAttribute("askBoardDto", new AskBoardDto());
		model.addAttribute("member", member);
		return "/mypage/QAwrite";
	}

	@PostMapping(value = "/QAwrite")
	public String qawrite(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,
			@Valid AskBoardDto askBoardDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("에러");
			return "/mypage/QAwrite";
		}
		try {
			AskBoard askBoard = AskBoard.createAskBoard(askBoardDto, member);
			askBoardService.write(askBoard);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/mypage/QAwrite";
		}
		return "redirect:/mypage/QAlist";
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
	public String qaview(
			@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member, Integer id,
			Model model) {
		AskBoard askBoard = askBoardService.boardView(id);
		model.addAttribute("askBoard", askBoard);
		model.addAttribute("member", member);
		return "/mypage/QAcontent";
	}

};
