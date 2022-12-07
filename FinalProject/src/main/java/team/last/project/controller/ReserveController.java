package team.last.project.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.ReserveDto;
import team.last.project.entity.Member;
import team.last.project.entity.OptPrice;
import team.last.project.entity.Reserve;
import team.last.project.entity.Room;
import team.last.project.service.MemberService;
import team.last.project.service.OptPriceService;
import team.last.project.service.OptionService;
import team.last.project.service.ReserveService;
import team.last.project.service.RoomService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/res")
public class ReserveController {
	private final ReserveService reserveService;
	private final RoomService roomService;
	private final OptionService optionService;
	private final OptPriceService optPriceService;
	private final MemberService memberService;

	@RequestMapping("")
	public String root(Model model) {
		List<Room> roomlist = roomService.roomList();
		model.addAttribute("roomlist", roomlist);
		return "/res/Reservation";
	}

	@RequestMapping("/room")
	public String layout(@RequestParam(value = "roomid") int roomid, Model model) {
		model.addAttribute("reserveDto", new ReserveDto());
		model.addAttribute("option", optionService.optionList());
		model.addAttribute("room", roomService.roomget(roomid).get());

		// 별점
		double score = 2.4;
		double star = score * 20;
		model.addAttribute("score", score);
		model.addAttribute("star", star);

		return "/res/Room";
	}

	@ResponseBody
	@RequestMapping("/optprice")
	public Map<String, Object> getoptPrice(@RequestParam("optprice_id") Integer optprice_id) {
		OptPrice optPrice = optPriceService.optPriceget(optprice_id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", optPrice.getId());
		result.put("content", optPrice.getContent());
		result.put("price", optPrice.getPrice());
		return result;

	}

	@PostMapping("/reserve/{id}")
	public String reserve(@PathVariable("id") Integer id, Authentication autentication, ReserveDto reserveDto) {
		Member member = memberService.memgetInfo(autentication.getName());
		Reserve reserve = Reserve.createReserve(reserveDto, member, roomService.roomget(id).get());
		reserveService.reserve(reserve);
		return "redirect:/res";
	}

	@ResponseBody
	@RequestMapping("/getreservedata")
	public List<Reserve> getreservedata(String month_id, HttpServletRequest request, Model model) {
		List<Reserve> reservelist = reserveService.findschedule(month_id);
		System.out.println(month_id);
		for (int i = 0; i < reservelist.size(); i++) {
			System.out.println(reservelist.get(i).getStart_time());
			System.out.println(reservelist.get(i).getEnd_time());
		}
		return reservelist;
	}
};
