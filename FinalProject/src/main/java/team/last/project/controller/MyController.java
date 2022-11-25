package team.last.project.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.RequiredArgsConstructor;
import team.last.project.entity.Option;
import team.last.project.service.OptPriceService;
import team.last.project.service.OptionService;

@Controller
@RequiredArgsConstructor
public class MyController {

	@Autowired
	OptPriceService optPriceService;
	@Autowired
	OptionService optionService;

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

	@RequestMapping("/res")
	public String Reservation() {
		return "Reservation";
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