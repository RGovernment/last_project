package team.last.project.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.last.project.constant.AskType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskBoardDto {

	@NotNull
	private String title;
	@NotNull
	private AskType type;
	@NotNull
	private String content;
	
	private String answer;
}
