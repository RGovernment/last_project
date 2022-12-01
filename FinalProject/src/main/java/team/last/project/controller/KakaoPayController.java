package team.last.project.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.AmountDto;
import team.last.project.dto.KakaoPayApprovalDto;
import team.last.project.dto.PayDto;
import team.last.project.service.MemberService;
import team.last.project.service.PayService;

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoPayController {

	KakaoPayApprovalDto kakao;
	private MemberService memberService;
	private PayService payService;

	@RequestMapping("/kakaoPay")
	@ResponseBody
	public Object kakaopay(@RequestParam("name") String name,
			@RequestParam("item") String item,
			@RequestParam("price") String price) throws ParseException {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
			// url 을 사용하려면 trycatch가 필요하다
			payconnect.setRequestMethod("POST");
			// kakao 에서 포스트형식으로 넘겨달라고햇다
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			// 카카오에서 발급해준 어드민키 를 authorization 형식으로 넘겨달라고 카카오에서 시켯다
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// 컨텍트 타입도 카카오에서 시키는대로 했다
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);
			// 요기까지 기본설정
			
			Date date = new Date();
			
			SimpleDateFormat order_format = new SimpleDateFormat("yyyyMMdd");
			int random =((int) (Math.random()*100))+1;
			
			String order_id = order_format.format(date)+random;
			kakao = new KakaoPayApprovalDto();
			kakao.setPartner_order_id(order_id);
			kakao.setPartner_user_id(name);
			
			String parameter = "cid=TC0ONETIME&" 
					+ "partner_order_id="+order_id+"&" //주문번호
					+ "partner_user_id="+name+"&" //주문자이름
					+ "item_name="+item+"&" //상품이름
					+ "quantity=1&" //상품갯수
					+ "total_amount="+price+"&" //상품가격
					+ "tax_free_amount=0&" //비과세액
					+ "approval_url=http://localhost:8082/kakao/success&" // 성공 주소
					+ "fail_url=http://localhost:8082/kakao/fail&" //실패 주소
					+ "cancel_url=http://localhost:8082/kakao/cancel"; //취소 주소

			OutputStream DataPakage = payconnect.getOutputStream();
			// outputstream은 서버에서 주고싶은 데이터를 담는다.

			DataOutputStream throwData = new DataOutputStream(DataPakage);
			// 아웃풋 스트림에 담긴 데이터를 카카오로 던져주는아이
			throwData.writeBytes(parameter);
			// 카카오에서 바이트형식으로 달라했으니 바이트형식으로 형변환을 해준다.

			throwData.flush();
			// 본인한테 담겨있는 데이터를 카카오로 넘김과 동시에 본인을 비운다.
			throwData.close();
			// 이제 더이상 데이터 보낼게 없음으로 닫는다 flush 없이 해당 문구만써도 자동으로 flush 해준다.

			int resultset = payconnect.getResponseCode();
			// 결과를 이제 받아온다.

			InputStream resultData;
			// 결과를 받아온놈을 이제 자바에서 읽을수있도록 바꿔주는애? 인거같다.

			if (resultset == 200) // 성공코드가 200이라고한다. 나머지는 전부 fail이라고한다.
			{
				resultData = payconnect.getInputStream();
			} else {
				resultData = payconnect.getErrorStream();
			}
			InputStreamReader resultReader = new InputStreamReader(resultData);
			// 자바에서 문자열로 읽을수잇게 형변환해준다

			// ("next_redirect_pc_url")

//			 String[] a = changeResult.readLine().split(",");

			// System.out.println(a[0]);
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
	public String kakaoPaySuccess(Authentication auth,@RequestParam("pg_token") String pg_token, Model model) throws ParseException {
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
			memberService.memgetInfo(auth.getName());
			paydto.setItem(jsonObject.getAsString("item_name"));
			paydto.setPay_amount((Integer) jsonObject2.get("total"));
			paydto.setPay_date_time(date);
			paydto.setTid(kakao.getTid());
			
			payService
			
			/*
			KakaoPayApprovalDto kakaoAdto = new KakaoPayApprovalDto();
			AmountDto amount2 = new AmountDto();
			kakaoAdto.setAmount(amount2);
			amount2.setTotal((Integer) jsonObject2.get("total"));
			kakaoAdto.setApproved_at(date);
			kakaoAdto.setPartner_order_id(jsonObject.getAsString("partner_user_id"));
			kakaoAdto.setItem_name(jsonObject.getAsString("item_name"));
			kakaoAdto.setQuantity((Integer) jsonObject.get("quantity"));
			kakaoAdto.getAmount().setTotal(amount2.getTotal());
			kakaoAdto.setPayment_method_type(jsonObject.getAsString("payment_method_type"));
			*/
			
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
		model.addAttribute("message","결제가 완료되었습니다.");
		model.addAttribute("Url","/res");
		return "/kakao/message";
	}

	@RequestMapping("/pay")
	public String kakaopay2() {
		return "/kakao/kakaoPaySuccess";
	}
}
