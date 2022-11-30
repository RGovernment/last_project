package team.last.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.RequiredArgsConstructor;
import team.last.project.entity.Option;
import team.last.project.entity.Review;
import team.last.project.service.OptionService;
import team.last.project.service.ReviewService;

@Controller
@RequiredArgsConstructor
public class MyController {

	private final OptionService optionService;
	private final ReviewService reviewService;
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
	public String reviewlist(Authentication authentication, Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Review> list = null;
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());

		list = reviewService.reviewList(pageRequest);
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		if (endPage == 0) {
			endPage = 1;
		}

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/review";
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