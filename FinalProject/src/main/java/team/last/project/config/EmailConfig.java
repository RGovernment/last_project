package team.last.project.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;
	@Value("${spring.mail.username}")
	private String id;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.properties.default.encoding}")
	private String encode;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;

	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setUsername(id);
		javaMailSender.setPassword(password);
		javaMailSender.setPort(port);
		javaMailSender.setJavaMailProperties(getMailProperties());
		javaMailSender.setDefaultEncoding(encode);
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return props;
	}
}
