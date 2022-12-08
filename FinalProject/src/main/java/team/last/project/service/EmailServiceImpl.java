package team.last.project.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender emailSender;
	
	private String ePw;

	private MimeMessage createMessage(String to) throws Exception {
		ePw=createKey();
		MimeMessage message = emailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("'Memory Gift 회원가입 인증 메일'");// 제목

		String msgg = "";
		msgg += "<div align='center'>";
		msgg += "<div style='background-color:#dfdfdf; width:600px;'>";
		msgg += "<div style='padding:15px; margin:10px;'>";
		msgg += "<h2 style='font-style:bold; font-size:200%'> Memory Gift </h2>";
		msgg += "<br>";
		msgg += "<h2> Memory Gift 회원가입을 위한 인증번호입니다. </h2>";
		msgg += "<br>";
		msgg += "<p>아래 인증번호를 확인하여<p>";
		msgg += "<p>이메일 주소 인증을 완료해 주세요.<p>";
		msgg += "<br>";
		msgg += "<hr>";
		msgg += "<h3 style='color:#483d8b; font-style:bold;'>회원가입 인증 코드입니다.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "인증번호 : <strong>";
		msgg += ePw + "</strong><div><br/> ";
		msgg += "</div>";
		msgg += "<hr>";
		msgg += "<div style='color:#1f1f1f; font-size:12px;'>본 메일은 발신전용입니다.</div>";
		msgg += "<div style='color:#1f1f1f; font-size:12px;'>회원가입과 관련하여 궁금한 점이 있으시면 문의해주세요.</div>";
		msgg += "<div style='color:#1f1f1f; font-size:12px; style='margin-bottom:5px;'>Copyright © Memory Gift All rights reserved.</div>";
		msgg += "</div>";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용
		message.setFrom(new InternetAddress("wjdqn1324@gmail.com", "memory Gift"));// 보내는 사람

		return message;
	}

	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}
		return key.toString();
	}

	@Override
	public String sendSimpleMessage(String to) throws Exception {
		MimeMessage message = createMessage(to);
		try {// 예외처리
			emailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
		return ePw;
	}

}
