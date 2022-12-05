package team.last.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import team.last.project.entity.Review;
import team.last.project.entity.Room;
import team.last.project.service.MemberService;
import team.last.project.service.OptPriceService;
import team.last.project.service.OptionService;
import team.last.project.service.ReserveService;
import team.last.project.service.ReviewService;
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
	private final ReviewService reviewService;

	@RequestMapping("")
	public String root(Model model, HttpServletRequest req) {
		// 예약 결제 세션 초기화//
		if (req.getSession().getAttribute("reserve") != null) {
			req.getSession().removeAttribute("reserve");
		}

		List<Room> roomlist = roomService.roomList();
		model.addAttribute("roomlist", roomlist);
		return "/res/Reservation";
	}

	@RequestMapping("/room")
	public String layout(@RequestParam(value = "roomid") int roomid, Model model) {
		model.addAttribute("reserveDto", new ReserveDto());
		model.addAttribute("option", optionService.optionList());
		model.addAttribute("room", roomService.roomget(roomid).get());
		return "/res/Room";
	}

	@ResponseBody
	@PostMapping("/optprice")
	public Map<String, Object> getoptPrice(@RequestParam("optprice_id") Integer optprice_id) {
		OptPrice optPrice = optPriceService.optPriceget(optprice_id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", optPrice.getId());
		result.put("content", optPrice.getContent());
		result.put("price", optPrice.getPrice());
		return result;

	}

	@PostMapping("/reserve/{id}")
	public String reserve(@PathVariable("id") Integer id, Authentication autentication, ReserveDto reserveDto,
			Model model, HttpServletRequest req) {
		Member member = memberService.memgetInfo(autentication.getName());
		Reserve reserve = Reserve.createReserve(reserveDto, member, roomService.roomget(id).get());
		Room room = roomService.roomget(id).get();
		String roomname = room.getName();
		model.addAttribute("member", member);
		model.addAttribute("reservedto", reserveDto);
		model.addAttribute("item", roomname);
		req.getSession().setAttribute("reserve", reserve);
		return "/kakao/kakaoPay";
	}

	// 달력에 예약 현황을 보여주기 위한 요청URL
	@ResponseBody
	@PostMapping("/getreservedata")
	public List<Map<String, Object>> getreservedata(String month_id, HttpServletRequest request, Model model) {
		List<Reserve> reservelist = reserveService.findschedule(month_id);
		List<Map<String, Object>> reserveMapList = new ArrayList<Map<String, Object>>();
		if (reservelist != null) {
			for (int i = 0; i < reservelist.size(); i++) {
				Map<String, Object> reserveMap = new HashMap<String, Object>();
				String SDay = new SimpleDateFormat("dd").format(reservelist.get(i).getStart_time());
				String SHour = new SimpleDateFormat("HH").format(reservelist.get(i).getStart_time());
				String EHour = new SimpleDateFormat("HH").format(reservelist.get(i).getEnd_time());

				reserveMap.put("SDay", SDay);
				reserveMap.put("SHour", Integer.parseInt(SHour));
				reserveMap.put("EHour", Integer.parseInt(EHour));
				reserveMapList.add(reserveMap);
			}
		} // 필요한 데이터인 날짜와 시작,끝 시간을 가공해 HashMap 형태로 묶은 후 List에 담아서 return
		return reserveMapList;
	}

	// 별점평균 만들기 요청 URL
	@ResponseBody
	@PostMapping("/staravg")
	public int staravg(@RequestParam(value = "room_id") int room_id) {
		System.out.println("test");
		int staravg = 0;
		int totalscore = 0;
		List<Review> reviewlist = reviewService.reviewbyroomid(room_id);
		for (Review r : reviewlist) {
			totalscore += r.getScore();
		}
		totalscore = totalscore / reviewlist.size();
		staravg = totalscore * 20;
		System.out.println(staravg);
		return staravg;
	}
};
