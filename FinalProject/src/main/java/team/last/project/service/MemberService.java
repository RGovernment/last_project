package team.last.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public void deleteMember(Member member) {
		 memberRepository.delete(member);
	}
	
	public void deleteMember(String email) {
		Member member = memberRepository.findByEmail(email).orElse(null);
		memberRepository.delete(member);
	}
	
	public List<Member> memberList() {
		return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	public Page<Member> memberListPaging(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}

	
	public boolean memberck(String password,Member member) {
		return passwordEncoder.matches(password, member.getPassword());
	}
	
	public Member memgetInfo(String email) {
		Member mem= memberRepository.findByEmail(email).orElse(null);
		return mem;
	}
	
	public String memgetName(String email) {
		Member mem= memberRepository.findByEmail(email).orElse(null);
		String name = mem.getName();
		return name;
	}
	
	public Long memgetId(String email) {
		Member mem= memberRepository.findByEmail(email).orElse(null);
		Long id = mem.getId();
		return id;
	}
	
	@Transactional
	public void edit(Member member,String name,String phone) {
		Member mem = memberRepository.findByEmail(member.getEmail()).orElse(null);
		mem.modifyMember(name,phone);
	}
	
	@Transactional
	public void editPass(Member member,String password) {
		Member mem = memberRepository.findByEmail(member.getEmail()).orElse(null);
		String pass= passwordEncoder.encode(password);
		mem.modifyPass(pass);
	}
	
	@Transactional
	public void restore(String email) {
		Member mem = memberRepository.findByEmail(email).orElse(null);
		mem.restoreMember();
	}
	
	public Member getMemberK(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
		if (findMember != null) {
			join(member);
		}
		return findMember;
	}
	//회원탈퇴 기능과 분리 2022.12.08
	public Member getbyEmail(String email) {
		Member member = memberRepository.findByEmail(email).orElse(null);
		return member;
		
	}
	
	public void secessionMember(String email) {
		Member member = getbyEmail(email);
		member.secessionMember();
	}


}
