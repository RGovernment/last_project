package team.last.project.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;
import team.last.project.dto.KakaoPayApprovalDto;
import team.last.project.dto.KakaopayReadyDto;

@Service
@Log
public class KakaopayService {

	private static final String Host = "https://kapi.kakao.com";

	private KakaopayReadyDto kakaopayReadyDto;
	private KakaoPayApprovalDto kakaopayApprovalDto;

	

	public KakaoPayApprovalDto kakaoPayInfo(String pg_token) {

		RestTemplate restTemplate = new RestTemplate();
		// 서버로 요청할 Header

		HttpHeaders headers = new HttpHeaders();
		headers.add("Autorization", "KakaoAK" + "admin key를 넣어주세요~~!");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaopayReadyDto.getTid());
		params.add("partner_order_id", "1001");
		params.add("partner_user_id", "gorany");
		params.add(pg_token, pg_token);
		params.add("total_amount", "2100");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaopayApprovalDto = restTemplate.postForObject(new URI(Host + "/v1/payment/approve"), body,
					KakaoPayApprovalDto.class);
			log.info("" + kakaopayApprovalDto);

			return kakaopayApprovalDto;

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
