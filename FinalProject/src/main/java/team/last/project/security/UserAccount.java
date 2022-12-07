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

	@Override
	public boolean isEnabled() {

		if (member.getSecession() != 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getUsername() {
		return member.getEmail();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof UserAccount){
			return  this.getUsername().equals(((UserAccount) obj).getUsername());
		}
	return false;
	}

	@Override
	public int hashCode(){
		return this.getUsername().hashCode();
	}
	
	

}
