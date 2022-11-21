package team.last.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.MemberDto;
import team.last.project.dto.OptionDto;
import team.last.project.entity.Member;
import team.last.project.entity.Option;
import team.last.project.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

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

//	public void edit(Integer id, final MemberDto memberDto) {
//		Member mem = memberRepository.findById(id).orElseThrow();
//		mem.modifyMember(memberDto.getName(), memberDto.getPhone(), memberDto.getGender());
//	}

	public void getbyEmail(String email) {
		Member member = memberRepository.findByEmail(email).orElse(null);

		member.secessionMember();
	}

}
