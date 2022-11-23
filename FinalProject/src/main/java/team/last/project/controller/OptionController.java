package team.last.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import team.last.project.constant.Option_type;
import team.last.project.dto.OptPriceDto;
import team.last.project.dto.OptionDto;
import team.last.project.entity.Member;
import team.last.project.entity.OptPrice;
import team.last.project.entity.Option;
import team.last.project.service.OptPriceService;
import team.last.project.service.OptionService;

@Controller
@RequestMapping("/admin/option")
@RequiredArgsConstructor
public class OptionController {
	@Autowired
	private OptionService optionService;
	@Autowired
	private OptPriceService optPriceService;
	
	@RequestMapping(value = "/list")
	public String optionlist(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,Model model) {
		List<Option> optionlist = optionService.optionList();
		model.addAttribute("member",member);
		model.addAttribute("optionlist", optionlist);
		return "/admin/option/optionlist";
	}

	@GetMapping(value = "/add")
	public String optionaddform(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,Model model) {
		model.addAttribute("member",member);
		model.addAttribute("Option_types", Option_type.values());
		model.addAttribute("optionDto", new OptionDto());
		return "/admin/option/optionaddform";
	}

	@PostMapping(value = "/add")
	public String optionadd(OptionDto optionDto, BindingResult bindingResult, Model model) {
		Option option = Option.createOption(optionDto);
		optionService.optionAdd(option);
		return "redirect:/admin/option/list";
	}
	
	@GetMapping(value = "/update/{id}")
	public String optionupdateform(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member, @PathVariable("id") Integer id, Model model) {
		model.addAttribute("option", optionService.optionget(id));
		model.addAttribute("member",member);
		return "/admin/option/optionupdate";
	}

	@PostMapping(value = "/update/{id}")
	public String optionupdate(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,@PathVariable("id") Integer id, OptionDto optionDto, Model model) {
		optionService.optionUpdate(id, optionDto);
		model.addAttribute("member",member);
		model.addAttribute("message", "옵션 수정 완료");
		model.addAttribute("Url", "/admin/option/list");
		return "/admin/option/Message";
	}

	@RequestMapping(value = "delete")
	public String optiondelete(Integer id) {
		optionService.optionDelete(id);
		return "redirect:/admin/option/list";
	}
	@RequestMapping(value = "/price/list/{id}")
	public String optionpricelist(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,@PathVariable("id") Integer id,Model model) {
		List<OptPrice> optionpricelist = optPriceService.optPriceListget(id);
		model.addAttribute("member",member);
		model.addAttribute("option_prices", optionpricelist);
		return "/admin/option/optpricelist";
	}

	@GetMapping(value = "/price/add/{id}")
	public String optionpriceaddform(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,@PathVariable("id") Integer id, Model model) {
		model.addAttribute("optPriceDto", new OptPriceDto());
		model.addAttribute("member",member);
		model.addAttribute("option", optionService.optionDtoget(id));
		model.addAttribute("id", id);
		return "/admin/option/optpriceaddform";
	}

	@PostMapping(value = "/price/add/{id}")
	public String optionpriceadd(@PathVariable("id") Integer id, OptPriceDto optPriceDto, BindingResult bindingresult,
			Model model) {

		OptPrice optPrice = OptPrice.createOptPrice(optPriceDto, optionService.optionget(id));
		optPriceService.optPriceAdd(optPrice);
		return "redirect:/admin/option/list";
	}
	@GetMapping(value = "/price/update/{id}")
	public String optionpriceupdateform(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member") Member member,@PathVariable("id") Integer id, Model model) {
		model.addAttribute("optprice", optPriceService.optPriceget(id));
		model.addAttribute("member",member);
		return "/admin/option/optpriceupdate";
	}
	@PostMapping(value = "/price/update/{id}")
	public String optionpriceupdate(@PathVariable("id") Integer id, OptPriceDto optPriceDto, Model model) {
		optPriceService.optPriceUpdate(id, optPriceDto);
		model.addAttribute("message", "옵션 수정 완료");
		model.addAttribute("Url", "/admin/option/list");
		return "/admin/option/Message";
	}
	@RequestMapping(value = "/price/delete/{id}")
	public String optionpricedelete(@PathVariable("id") Integer id) {
		optPriceService.optPriceDelete(id);
		return "redirect:/admin/option/list";
	}
}
