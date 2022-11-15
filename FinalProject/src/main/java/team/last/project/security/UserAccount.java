package team.last.project.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import team.last.project.entity.Member;

@Getter
public class UserAccount extends User {
	private Member member;

	public UserAccount(Member member) {
		super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + member.getRole())));
		this.member = member;
	}
}
