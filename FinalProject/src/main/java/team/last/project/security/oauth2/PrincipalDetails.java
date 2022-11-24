package team.last.project.security.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import team.last.project.constant.Role;
import team.last.project.entity.Member;

public class PrincipalDetails implements UserDetails, OAuth2User {
	private static final String ROLE_PREFIX = "ROLE_";
	private Member member;
	// private Map<String, Object> attributes;
	private KakaoUserInfo kakaoUserInfo;

	// MemberDetails : Form 로그인 시 사용
	public PrincipalDetails(Member member) {
		this.member = member;
	}

	// OAuth2Member : OAuth2 로그인 시 사용
	// public PrincipalDetails(Member Member, Map<String, Object> attributes) {
	// //PrincipalOauth2MemberService 참고
	// this.Member = Member;
	// this.attributes = attributes;
	// }

	public PrincipalDetails(Member member, KakaoUserInfo kakaoUserInfo) {
		this.member = member;
		this.kakaoUserInfo = kakaoUserInfo;
	}

	/**
	 * MemberDetails 구현 해당 유저의 권한목록 리턴
	 */
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = member.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + role.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //List인 이유 : 여러개의 권한을 가질 수 있다
        authorities.add(authority);

        return authorities;
    }
	/**
	 * MemberDetails 구현 비밀번호를 리턴
	 */
	@Override
	public String getPassword() {
		return member.getPassword();
	}

	/**
	 * MemberDetails 구현 PK값을 반환해준다
	 */
	@Override
	public String getUsername() {
		return member.getEmail();
	}

	/**
	 * MemberDetails 구현 계정 만료 여부 true : 만료안됨 false : 만료됨
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * MemberDetails 구현 계정 잠김 여부 true : 잠기지 않음 false : 잠김
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * MemberDetails 구현 계정 비밀번호 만료 여부 true : 만료 안됨 false : 만료됨
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * MemberDetails 구현 계정 활성화 여부 true : 활성화됨 false : 활성화 안됨
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * OAuth2Member 구현
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getAttributes() {
		// return attributes;
		return kakaoUserInfo.getAttributes();
	}

	/**
	 * OAuth2Member 구현
	 * 
	 * @return
	 */
	@Override
	public String getName() {
		// String sub = attributes.get("sub").toString();
		// return sub;
		return kakaoUserInfo.getProviderId();
	}
}
