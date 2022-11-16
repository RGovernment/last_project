package team.last.project.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import team.last.project.constant.Role;
import team.last.project.dto.MemberDto;

@Entity
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
    @SequenceGenerator(sequenceName = "member_id_seq", allocationSize = 1, name = "ID_SEQ")
	private Long id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private int	gender;
	@Enumerated(EnumType.ORDINAL)
    private Role role;

	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
		member.setPhone(memberDto.getPhone());
		member.setGender(memberDto.getGender());
		String password = passwordEncoder.encode(memberDto.getPassword());
		member.setPassword(password);
		member.setRole(Role.USER);
        return member;
	}
}