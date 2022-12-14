package team.last.project.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptPriceDto {
	@NotNull
	private String content;
	@NotNull
	private int price;
}
