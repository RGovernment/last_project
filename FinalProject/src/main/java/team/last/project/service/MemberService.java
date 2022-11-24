package team.last.project.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;
import team.last.project.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

	public Member join(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	public List<Member> memberList() {
		return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public void edit(Member member,String name,String phone) {
		Member mem = memberRepository.findByEmail(member.getEmail()).orElse(null);
		mem.modifyMember(name,phone);
	}
	
	public boolean memberck(String password,Member member) {
		return passwordEncoder.matches(password, member.getPassword());
		
	}
	
	@Transactional
	public void editPass(Member member,String password) {
		Member mem = memberRepository.findByEmail(member.getEmail()).orElse(null);
		String pass= passwordEncoder.encode(password);
		mem.modifyPass(pass);
	}
	
	public Member getMemberK(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
		if (findMember != null) {
			join(member);
		}
		return findMember;
	}

	public void getbyEmail(String email) {
		Member member = memberRepository.findByEmail(email).orElse(null);

		member.secessionMember();
	}

}
