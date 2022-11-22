package team.last.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 아닙니다")
	private String email;
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	@Length(max = 35, message = "이름이 너무 깁니다.")
	private String name;
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
	@Pattern(regexp = "^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,16}$", message = "비밀번호의 형식이 틀렸습니다.")
	private String password;
	@NotNull
	@Pattern(regexp = "^010[.-]?(\\d{4})[.-]?(\\d{4})$", message = "전화번호의 형태가 아닙니다.")
	private String phone;
	@NotNull
	private int secession;
	private int gender;

}
