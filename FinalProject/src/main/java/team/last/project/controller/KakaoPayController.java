package team.last.project.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.KakaoPayApprovalDto;
import team.last.project.dto.PayDto;
import team.last.project.entity.Member;
import team.last.project.entity.Pay;
import team.last.project.entity.Reserve;
import team.last.project.service.MemberService;
import team.last.project.service.PayService;
import team.last.project.service.ReserveService;

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoPayController {

	KakaoPayApprovalDto kakao;
	private final MemberService memberService;
	private final PayService payService;
	private final ReserveService reserveService;
	
	@PostMapping("/kakaoPay")
	@ResponseBody
	public Object kakaopay(@RequestParam("name") String name,
			@RequestParam("item") String item,
			@RequestParam("price") String price) throws ParseException {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
			
			payconnect.setRequestMethod("POST");
			// post ?????? ??????
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			// admin??? authorization??? ??????
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// ????????? ??????
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);
			//???????????? end
			
			Date date = new Date();
			
			SimpleDateFormat order_format = new SimpleDateFormat("yyyyMMdd");
			int random =((int) (Math.random()*100))+1;
			
			String order_id = order_format.format(date)+random;
			String encodeResult = URLEncoder.encode(item, "UTF-8");
			kakao = new KakaoPayApprovalDto();
			kakao.setPartner_order_id(order_id);
			kakao.setPartner_user_id(name);
			
			String parameter = "cid=TC0ONETIME&" 
					+ "partner_order_id="+order_id+"&" //????????????
					+ "partner_user_id="+name+"&" //???????????????
					+ "item_name="+encodeResult+"&" //????????????
					+ "quantity=1&" //????????????
					+ "total_amount="+price+"&" //????????????
					+ "tax_free_amount=0&" //????????????
					+ "approval_url=http://192.168.0.16:8082/kakao/success&" // ?????? ??????
					+ "fail_url=http://192.168.0.16:8082/kakao/fail&" //?????? ??????
					+ "cancel_url=http://192.168.0.16:8082/kakao/cancel"; //?????? ??????

			OutputStream DataPakage = payconnect.getOutputStream();
			//????????? ?????? ????????? Packaging

			DataOutputStream throwData = new DataOutputStream(DataPakage);
			// ???????????? ??????
			throwData.writeBytes(parameter);
			// ???????????? ?????????

			throwData.flush();
			throwData.close();
			// ?????? close, flush??? close??? ???????????? ?????????

			int resultset = payconnect.getResponseCode();
			// ??????

			InputStream resultData;

			if (resultset == 200) // ???????????? = 200
			{
				resultData = payconnect.getInputStream();
			} else {
				resultData = payconnect.getErrorStream();
			}
			InputStreamReader resultReader = new InputStreamReader(resultData);

			@SuppressWarnings("deprecation")
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(resultReader);

			kakao.setTid((String) jsonObject.get("tid"));

			// return changeResult.readLine();
			return jsonObject;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/success")
	public String kakaoPaySuccess(Authentication auth,@RequestParam("pg_token") String pg_token, Model model,HttpServletRequest req) throws ParseException {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/approve");
			HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
			payconnect.setRequestMethod("POST");
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);

			String parameter = 
					"cid=TC0ONETIME&" 
					+ "tid=" + kakao.getTid() + "&" //???????????? 
					+ "partner_order_id="+kakao.getPartner_order_id() +"&" //????????????
					+ "partner_user_id="+kakao.getPartner_user_id()+"&"  //
					+ "pg_token=" + pg_token;

			OutputStream DataPakage = payconnect.getOutputStream();
	
			DataOutputStream throwData = new DataOutputStream(DataPakage);
			
			throwData.writeBytes(parameter);
			throwData.flush();
			throwData.close();

			int resultset = payconnect.getResponseCode();
 
			InputStream resultData;

			if (resultset == 200) 
			{
				resultData = payconnect.getInputStream();
			} else {
				resultData = payconnect.getErrorStream();
			}
			InputStreamReader resultReader = new InputStreamReader(resultData);

			@SuppressWarnings("deprecation")
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(resultReader);
			JSONObject jsonObject2 = (JSONObject) jsonObject.get("amount");
			
			LocalDateTime local = LocalDateTime.parse((String) jsonObject.get("approved_at"));
			
			Timestamp date = Timestamp.valueOf(local);
			
			PayDto paydto = new PayDto();
			Member mem= memberService.memgetInfo(auth.getName());
			paydto.setItem(jsonObject.getAsString("item_name"));
			paydto.setPay_amount((Integer) jsonObject2.get("total"));
			paydto.setPay_date_time(date);
			paydto.setTid(kakao.getTid());
			paydto.setOrdercode(kakao.getPartner_order_id());
			Pay pay = Pay.Pay_success(paydto,mem);
			payService.savepay(pay);
			//?????? ????????? Pay DB ?????? 
			
			Reserve reserve =(Reserve) req.getSession().getAttribute("reserve");
			reserve.addPay(pay);
			reserveService.reserve(reserve);
			//?????? ????????? Reserve DB ?????? 
			
			model.addAttribute("message","????????? ?????????????????????.");
			model.addAttribute("Url","/res");
			return "/kakao/message";

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/fail")
	public String kakaoPayfail(Model model) {
		model.addAttribute("message","????????? ??????????????????.");
		model.addAttribute("Url","/res");
		return "/kakao/message";
	}

	@GetMapping("/cancel")
	public String kakaoPayCancel(Model model) {
		model.addAttribute("message","????????? ?????? ?????????????????????.");
		model.addAttribute("Url","/res");
		return "/kakao/message";
	}

	@GetMapping("/refund")
	public String kakaoPayRefund(@RequestParam("resid")Long resid , Model model,Authentication authentication) throws ParseException {
		try {
			Reserve reserve = reserveService.get(resid);
			Pay pay = payService.get(reserve.getPay().getId());
			URL address = new URL("https://kapi.kakao.com/v1/payment/cancel");
			HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
			payconnect.setRequestMethod("POST");
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);
			
			
			String tid= pay.getTid();
			int amount = pay.getPay_amount();
			 
			String parameter = 
					"cid=TC0ONETIME&" 
					+ "tid=" + tid + "&" //???????????? 
					+ "cancel_amount="+ amount +"&"
					+ "cancel_tax_free_amount=0"; 

			OutputStream DataPakage = payconnect.getOutputStream();
	
			DataOutputStream throwData = new DataOutputStream(DataPakage);
			
			throwData.writeBytes(parameter);
			throwData.flush();
			throwData.close();

			int resultset = payconnect.getResponseCode();
 
			@SuppressWarnings("unused")
			InputStream resultData;
			if (resultset == 200) 
			{
				payService.delete(pay);
				resultData = payconnect.getInputStream();
			} else {
				resultData = payconnect.getErrorStream();
			}

			model.addAttribute("message","????????? ?????????????????????.");
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
			if(!authList.get(0).getAuthority().contains("ADMIN")) {
				model.addAttribute("Url","/mypage");
			}else {
				model.addAttribute("Url","/admin");
			}
			
			return "/kakao/message";

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


@GetMapping("/secession/{email}")
public String kakaosecession(@PathVariable("email") String email,Model model) throws ParseException {
	try {
		URL address = new URL("https://kapi.kakao.com/v1/user/unlink");
		HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
		payconnect.setRequestMethod("POST");
		payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
		payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		payconnect.setDoInput(true);
		payconnect.setDoOutput(true);
		 
		String[] a  = email.split("@");
		
		String id = a[0];
		
		String parameter = "target_id_type=user_id&"//???????????? ??????, user_id??? ??????
				+ "target_id="+ id; 

		OutputStream DataPakage = payconnect.getOutputStream();

		DataOutputStream throwData = new DataOutputStream(DataPakage);
		
		throwData.writeBytes(parameter);
		throwData.flush();
		throwData.close();

		int resultset = payconnect.getResponseCode();

		@SuppressWarnings("unused")
		InputStream resultData;

		if (resultset == 200) 
		{
			memberService.deleteMember(email);
			resultData = payconnect.getInputStream();
		} else {
			resultData = payconnect.getErrorStream();
		}
		
		
		model.addAttribute("secessionMsg","????????? ?????????????????????.");
		
		
		return "/member/secession_msg";

	} catch (MalformedURLException e) {

		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

}

