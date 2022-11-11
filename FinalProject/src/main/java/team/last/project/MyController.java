package team.last.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@RequestMapping("/")
	public String root() throws Exception {
		return "redirect:index";
	}

	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}

	@RequestMapping("/login")
	public String loginPage() {

		return "login";
	}

	@RequestMapping("/card")
	public String login() {

		return "review";
	}

	@RequestMapping("/res")
	public String Reservation() {
		return "Reservation";
	}

	@RequestMapping("/terms")
	public String termsConditions() {
		return "terms";
	}

	@RequestMapping("/room")
	public String layout(@RequestParam(value="roomType") String roomType) {
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