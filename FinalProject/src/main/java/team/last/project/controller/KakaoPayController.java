package team.last.project.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.AmountDto;
import team.last.project.dto.KakaoPayApprovalDto;
import team.last.project.service.KakaopayService;

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoPayController {

	private final KakaopayService kakaopay;
	KakaoPayApprovalDto kakao;

	@RequestMapping("/kakaotest")
	@ResponseBody
	public Object kakaopay() throws ParseException {
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

			String parameter = "cid=TC0ONETIME&" + "partner_order_id=partner_order_id&"
					+ "partner_user_id=partner_user_id&" + "item_name=chicken&" + "quantity=1&" + "total_amount=2200&"
					+ "vat_amount=200&" + "tax_free_amount=0&" + "approval_url=http://localhost:8082/kakao/success&"
					+ "fail_url=/kakao/fail&" + "cancel_url=/kakao/cancel";
			// 카카오에서 ox 있는 길게 나와있는표에서 필요한것들만 넣었다

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
			BufferedReader changeResult = new BufferedReader(resultReader);

			// ("next_redirect_pc_url")

//			 String[] a = changeResult.readLine().split(",");

			// System.out.println(a[0]);
			@SuppressWarnings("deprecation")
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(resultReader);

			kakao = new KakaoPayApprovalDto();

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
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) throws ParseException {
		RestTemplate restTemplate = new RestTemplate();

		System.out.println("여기까지 오긴 함 ?");
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/approve");
			HttpURLConnection payconnect = (HttpURLConnection) address.openConnection();
			payconnect.setRequestMethod("POST");
			payconnect.setRequestProperty("Authorization", "KakaoAK e21543cbd75b9b045e2caaaacfd53b9f");
			payconnect.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			payconnect.setDoInput(true);
			payconnect.setDoOutput(true);

			String parameter = "cid=TC0ONETIME&" + "tid=" + kakao.getTid() + "&" + "partner_order_id=partner_order_id&"
					+ "partner_user_id=partner_user_id&" + "pg_token=" + pg_token;
			// 카카오에서 ox 있는 길게 나와있는표에서 필요한것들만 넣었다

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
			BufferedReader changeResult = new BufferedReader(resultReader);

			@SuppressWarnings("deprecation")
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(resultReader);
			JSONObject jsonObject2 = (JSONObject) jsonObject.get("amount");
			System.out.println(jsonObject);
			System.out.println(jsonObject2.get("total"));
			KakaoPayApprovalDto kakaoAdto = new KakaoPayApprovalDto();
			LocalDateTime local = LocalDateTime.parse((String) jsonObject.get("approved_at"));
			Date date = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
			String a = (String) jsonObject.get("approved_at");
			System.out.println(a);
			AmountDto amount2 = new AmountDto();
			kakaoAdto.setAmount(amount2);
			amount2.setTotal((Integer) jsonObject2.get("total"));
			kakaoAdto.setApproved_at(date);
			kakaoAdto.setPartner_order_id(jsonObject.getAsString("partner_user_id"));
			kakaoAdto.setItem_name(jsonObject.getAsString("item_name"));
			kakaoAdto.setQuantity((Integer) jsonObject.get("quantity"));
			kakaoAdto.getAmount().setTotal(amount2.getTotal());
			kakaoAdto.setPayment_method_type(jsonObject.getAsString("payment_method_type"));

			model.addAttribute("info", kakaoAdto);
			// return changeResult.readLine();
			return "/kakao/kakaoPaySuccess";

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/fail")
	public void kakaoPayfail(Model model) {
		// log.info("kakaoPaySuccess get.........");
		// log.info("kakaoPaySuccess pg_token : "+ pg_token);
		model.addAttribute("info", "실패");
	}

	@GetMapping("/cancel")
	public void kakaoPayCancel(Model model) {
		// log.info("kakaoPaySuccess get.........");
		// log.info("kakaoPaySuccess pg_token : "+ pg_token);
		model.addAttribute("info", "실패");
	}

	@RequestMapping("/pay")
	public String kakaopay2() {
		return "/kakao/kakaoPaySuccess";
	}
}
