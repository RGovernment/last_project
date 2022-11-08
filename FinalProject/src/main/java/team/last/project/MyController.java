package team.last.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("login")
	public String loginPage() {

		return "login";
	}

};