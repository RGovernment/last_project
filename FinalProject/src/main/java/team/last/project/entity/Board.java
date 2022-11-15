package team.last.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import team.last.project.dto.BoardDto;

@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_ID_SEQ")
    @SequenceGenerator(sequenceName = "board_id_seq", allocationSize = 1, name = "BOARD_ID_SEQ")
	private Integer id;
	private String title;
	private String content;
	public static Board createBoard(BoardDto boardDto) {
		Board board = new Board();
		board.setId(boardDto.getId());
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		return board;
	}
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
