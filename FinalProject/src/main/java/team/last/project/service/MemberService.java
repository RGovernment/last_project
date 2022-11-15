package team.last.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Member;
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
	private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
