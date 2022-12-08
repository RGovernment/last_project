package team.last.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;
import team.last.project.entity.Option;
import team.last.project.entity.Review;
import team.last.project.entity.Roomtype;
import team.last.project.service.MemberService;
import team.last.project.service.OptionService;
import team.last.project.service.ReviewService;
import team.last.project.service.RoomService;


@Controller
@RequiredArgsConstructor
public class MyController {
	
	private final OptionService optionService;
	private final MemberService memberService;
	private final ReviewService reviewService;
	private final RoomService roomService;
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

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
	public String indexPage() {		
		return "index";
	}

	@RequestMapping(value = "/errortest")
	public String erpage(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		String key = UUID.randomUUID().toString();
		
		AnonymousAuthenticationFilter token = new AnonymousAuthenticationFilter(key,"anonymousUser",AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
		
		AnonymousAuthenticationToken token2 = new AnonymousAuthenticationToken(key, token.getPrincipal(), token.getAuthorities());
		
		token.setAuthenticationDetailsSource(authenticationDetailsSource);
		
		token2.setDetails(authenticationDetailsSource.buildDetails(request));
		Authentication authentication = token2;
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		
		return "/member/login";
	}

	@RequestMapping("/card")
	public String reviewlist(Authentication authentication, Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,Integer roomid) {
		Page<Review> list = null;
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		//방 별 리뷰 가져오기 2022.12.07
		if(roomid == null) {
			list = reviewService.reviewList(pageRequest);
		}else {
			list = reviewService.reviewList(roomid,pageRequest);		
			}
		if (list.getTotalPages() != 0) {

			int nowPage = list.getPageable().getPageNumber() + 1;
			int startPage = Math.max(nowPage - 4, 1);
			int endPage = Math.min(nowPage + 5, list.getTotalPages());
			if (endPage == 0) {
				endPage = 1;
			}
			model.addAttribute("lastPage",list.getTotalPages());
			model.addAttribute("list", list);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			return "/review";
		} else {
			model.addAttribute("message", "작성된 후기가 없습니다.");
			model.addAttribute("Url", "/index");
			return "/er";
		}
	}

	@RequestMapping("/er")
	public String erpage2() {
		return "er";
	}
	
	@RequestMapping("/sessionerror")
	public String erpage3() {
		
		return "/sessionerror";
	}
	
	@RequestMapping("/sessionerror2")
	public String erpage4() {
		return "/sessionerror2";
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
		List<Roomtype> roomtypelist = roomService.roomtypelist();
		model.addAttribute("optlist", optlist);
		model.addAttribute("roomtypelist",roomtypelist);
		return "price";
	}
};