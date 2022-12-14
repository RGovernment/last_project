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
			// post 타입 요청
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			// admin키 authorization에 주입
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// 컨텍트 타입
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);
			//기본설정 end
			
			Date date = new Date();
			
			SimpleDateFormat order_format = new SimpleDateFormat("yyyyMMdd");
			int random =((int) (Math.random()*100))+1;
			
			String order_id = order_format.format(date)+random;
			String encodeResult = URLEncoder.encode(item, "UTF-8");
			kakao = new KakaoPayApprovalDto();
			kakao.setPartner_order_id(order_id);
			kakao.setPartner_user_id(name);
			
			String parameter = "cid=TC0ONETIME&" 
					+ "partner_order_id="+order_id+"&" //주문번호
					+ "partner_user_id="+name+"&" //주문자이름
					+ "item_name="+encodeResult+"&" //상품이름
					+ "quantity=1&" //상품갯수
					+ "total_amount="+price+"&" //상품가격
					+ "tax_free_amount=0&" //비과세액
					+ "approval_url=http://192.168.0.16:8082/kakao/success&" // 성공 주소
					+ "fail_url=http://192.168.0.16:8082/kakao/fail&" //실패 주소
					+ "cancel_url=http://192.168.0.16:8082/kakao/cancel"; //취소 주소

			OutputStream DataPakage = payconnect.getOutputStream();
			//서버에 보낼 데이터 Packaging

			DataOutputStream throwData = new DataOutputStream(DataPakage);
			// 카카오로 전송
			throwData.writeBytes(parameter);
			// 바이트로 형변환

			throwData.flush();
			throwData.close();
			// 전송 close, flush는 close에 자동으로 포함됨

			int resultset = payconnect.getResponseCode();
			// 결과

			InputStream resultData;

			if (resultset == 200) // 성공코드 = 200
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
					+ "tid=" + kakao.getTid() + "&" //요청번호 
					+ "partner_order_id="+kakao.getPartner_order_id() +"&" //주문번호
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
			//결제 성공시 Pay DB 전송 
			
			Reserve reserve =(Reserve) req.getSession().getAttribute("reserve");
			reserve.addPay(pay);
			reserveService.reserve(reserve);
			//결제 성공시 Reserve DB 전송 
			
			model.addAttribute("message","결제가 완료되었습니다.");
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
		model.addAttribute("message","결제가 실패했습니다.");
		model.addAttribute("Url","/res");
		return "/kakao/message";
	}

	@GetMapping("/cancel")
	public String kakaoPayCancel(Model model) {
		model.addAttribute("message","결제가 중도 취소되었습니다.");
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
					+ "tid=" + tid + "&" //요청번호 
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

			model.addAttribute("message","환불이 완료되었습니다.");
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
		
		String parameter = "target_id_type=user_id&"//회원번호 종류, user_id로 고정
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
		
		
		model.addAttribute("secessionMsg","탈퇴가 완료되었습니다.");
		
		
		return "/member/secession_msg";

	} catch (MalformedURLException e) {

		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

}

