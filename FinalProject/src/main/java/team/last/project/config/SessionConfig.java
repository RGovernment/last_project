package team.last.project.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

//@Configuration에 httpSessionEventPublisher 등록
@Configuration
public class SessionConfig {

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	@Component
	public class SessionDestroyedListener implements ApplicationListener<SessionDestroyedEvent> {

		@Override
		public void onApplicationEvent(SessionDestroyedEvent event) {
		}
	}
}


