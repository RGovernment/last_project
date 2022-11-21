package team.last.project.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.last.project.constant.Option_type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
	@NotNull
	private String name;
	private Option_type type;
	private String note;
}
