package team.last.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import team.last.project.constant.Role;
import team.last.project.dto.MemberDto;

@Entity
@Data
@DynamicInsert
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	@SequenceGenerator(sequenceName = "member_id_seq", allocationSize = 1, name = "ID_SEQ")
	private Long id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private int gender;
	@Column(columnDefinition = "number Default 0")
	private int secession;
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

	public void modifyMember(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public void modifyPass(String password) {
		this.password = password;
	}

	public void secessionMember() {
		this.secession = 1;
	}

}
