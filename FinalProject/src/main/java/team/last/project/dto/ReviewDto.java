package team.last.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	private int id;
	private String title;
	private String content;
	private int score;
}
