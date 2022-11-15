package team.last.project.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;
import team.last.project.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다."));
		return new UserAccount(member);
	}

}
