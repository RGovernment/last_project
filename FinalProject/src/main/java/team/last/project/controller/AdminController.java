package team.last.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.RequiredArgsConstructor;
import team.last.project.dto.AskBoardDto;
import team.last.project.entity.AskBoard;
import team.last.project.entity.Member;
import team.last.project.entity.OptPrice;
import team.last.project.entity.Reserve;
import team.last.project.entity.Room;
import team.last.project.service.AskBoardService;
import team.last.project.service.MemberService;
import team.last.project.service.OptPriceService;
import team.last.project.service.ReserveService;
import team.last.project.service.RoomService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final MemberService memberService;
	private final AskBoardService askBoardService;
	private final RoomService roomService;
	private final OptPriceService optpriceService;
	private final ReserveService reserveService;
	
	@RequestMapping(value = "")
	public String adminPage(Authentication authentication, Model model
			,@PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
			,@Nullable Integer sortingOrder) {
		
		String name = memberService.memgetName(authentication.getName());
		
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
		
		Page<Member> memberlist = memberService.memberListPaging(pageRequest);
		
		int nowPage = memberlist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, memberlist.getTotalPages());
		if (endPage == 0) {
			endPage = 1;
		}
		model.addAttribute("lastPage",memberlist.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("memberlist", memberlist);
		model.addAttribute("name", name);
		return "/admin/admin";
	}
	
	@PostMapping("/del_member")
	public String delUser(@RequestParam(value = "delEmail", required = false) String delEmail,Model model) {
		
		Member mem= memberService.memgetInfo(delEmail);
		memberService.deleteMember(mem);	
		model.addAttribute("message","회원탈퇴에 성공했습니다.");
		model.addAttribute("Url","/admin");
		
		return "/admin/message";
	}

	@RequestMapping(value = "/secessionOrder")
	public String adminPage2(Authentication authentication, Model model
			,@PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
			,@Nullable Integer sortingOrder) {
		
		String name = memberService.memgetName(authentication.getName());
		PageRequest pageRequest = null;
		if (sortingOrder == null)
			sortingOrder = 0;
		switch (sortingOrder) {
		case 0:
			pageRequest =  PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
			break;
		case 1:
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("secession").descending());
			break;
		}
		
		Page<Member> memberlist = memberService.memberListPaging(pageRequest);
		
		int nowPage = memberlist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, memberlist.getTotalPages());
		if (endPage == 0) {
			endPage = 1;
		}
		model.addAttribute("lastPage",memberlist.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("memberlist", memberlist);
		model.addAttribute("name", name);
		return "/admin/admin";
	}
	
	@RequestMapping("/room")
	public String room(Authentication authentication, Model model) {
		String name = memberService.memgetName(authentication.getName());
		model.addAttribute("name", name);
		
		List<Room> roomlist = roomService.roomList();
		model.addAttribute("roomlist", roomlist);
		
		return "/admin/room";
	}


	@PostMapping(value = "/QAlistsort")
	public String qaListSort(Authentication authentication, Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@Nullable Integer sortingOrder) {
		Page<AskBoard> list = null;
		PageRequest pageRequest = null;
		if (sortingOrder == null)
			sortingOrder = 0;
		switch (sortingOrder) {
		case 0:
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by("id").descending());
			break;
		case 1:
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by("answer").descending());
			break;
		case 2:
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by("answer").ascending());
			break;
		}
		String name = memberService.memgetName(authentication.getName());
		list = askBoardService.boardList(pageRequest);
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
		model.addAttribute("name", name);
		return "/admin/QAlist";
	}

	@RequestMapping(value = "/QAlist")
	public String qaList(Authentication authentication, Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<AskBoard> list = null;
		String name = memberService.memgetName(authentication.getName());
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		list = askBoardService.boardList(pageRequest);
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
		model.addAttribute("name", name);
		return "/admin/QAlist";
	}

	@RequestMapping("/resmanage")
	public String resmanage(Authentication authentication,Model model,
			@PageableDefault(page = 0, size = 10, sort = "start_time", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Reserve> list = null;
		String name = memberService.memgetName(authentication.getName());
		
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("start_time").descending());
		list = reserveService.allListPage(pageRequest);
		int pagecount = list.getNumberOfElements();
		List<OptPrice> optionlist = optpriceService.optPriceAllList();
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		if (endPage == 0) {
			endPage = 1;
		}
		model.addAttribute("lastPage",list.getTotalPages());
		model.addAttribute("pagenumber",pagecount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("reslist",list);
		model.addAttribute("oplist",optionlist);
		model.addAttribute("name",name);
		
		return "/admin/resmanagement";
	}
	
	@RequestMapping("/modiroom")
	public String layout(@RequestParam(value = "roomid") int roomid, Authentication authentication, Model model) {
		String name = memberService.memgetName(authentication.getName());
		model.addAttribute("room", roomService.roomget(roomid).get());
		model.addAttribute("name", name);
		return "/admin/Room_update";
	}

	@PostMapping("/room_update_name")
	public String room_update_name(Room room, Model model) {
		roomService.name_update(room.getId(), room.getName());
		return "redirect:/admin/room";
	}

	@PostMapping("/room_update_note")
	public String room_update_note(Room room, Model model) {
		roomService.note_update(room.getId(), room.getNote());
		return "redirect:/admin/room";
	}
	@PostMapping("/room_price_update")
	public String room_price_update(@RequestParam(value = "id") int id,
			@RequestParam(value = "price_num") String price_num, @RequestParam(value = "price") int price,
			Model model) {
		
		
		roomService.room_price_update(id, price_num, price);
		
		return "redirect:/admin/room";
	}
	@GetMapping(value = "/QAanswer")
	public String qaanswer(Authentication authentication, Integer id, Model model) {
		String name = memberService.memgetName(authentication.getName());
		AskBoard askBoard = askBoardService.boardView(id);
		model.addAttribute("askBoard", askBoard);
		model.addAttribute("name", name);
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
