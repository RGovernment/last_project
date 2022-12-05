package team.last.project.security.oauth2;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.last.project.constant.Role;
import team.last.project.entity.Member;
import team.last.project.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;

	@SuppressWarnings("unchecked")
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		Member member = new Member();
		OAuth2User oAuth2User = super.loadUser(userRequest);
        
		String email = "";
		Map<String, Object> attributes = oAuth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		Long id = Long.parseLong(attributes.get("id").toString());
		
		email = id + "@kakao.com";
		
		String nickname = properties.get("nickname").toString();

		if(null == memberRepository.findByEmail(email).orElse(null)) {
			member.setEmail(email);
			member.setName(nickname);
			member.setPassword("kakaologin");
			member.setPhone("-");
			member.setSecession(0);
			member.setGender(3);
			member.setRole(Role.KUSER);
		}
		KakaoUserInfo kakao2UserInfo = 
				new KakaoUserInfo(oAuth2User.getAttributes());
		Member byMemberEmail = memberRepository.findByEmail(email).orElse(null);
		if(byMemberEmail == null){
			byMemberEmail = member;
			memberRepository.save(byMemberEmail);
	        }
		
		// memberService.join(Member.createMemberforKakao(memberdto));
		//return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_KUSER")), attributes, "id");
		return new PrincipalDetails(byMemberEmail, kakao2UserInfo);	
	}
}