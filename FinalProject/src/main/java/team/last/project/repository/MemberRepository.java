package team.last.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.Member;
public interface MemberRepository extends JpaRepository<Member, Integer>{
    Optional<Member> findByEmail(String email);

}
