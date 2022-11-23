package team.last.project.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
import team.last.project.constant.AskType;
import team.last.project.dto.AskBoardDto;

@Entity
@Data
@Table(name = "ask_board")
@DynamicInsert
@NamedEntityGraph(name = "AskBoardWithMember", attributeNodes = @NamedAttributeNode("member"))
public class AskBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASK_BOARD_SEQ")
	@SequenceGenerator(sequenceName = "ask_board_seq", allocationSize = 1, name = "ASK_BOARD_SEQ")
	private int id;
	private String title;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ask_type")
	private AskType asktype;

	@Column(columnDefinition = "timestamp default current_timestamp")
	private Timestamp cdate;

	private String answer;

	public static AskBoard createAskBoard(AskBoardDto askBoardDto, Member member) {
		AskBoard askBoard = new AskBoard();
		askBoard.setTitle(askBoardDto.getTitle());
		askBoard.setContent(askBoardDto.getContent());
		askBoard.setAsktype(askBoardDto.getType());
		askBoard.setMember(member);
		return askBoard;
	}

	public void update(String title, String content, String answer) {
		this.title = title;
		this.content = content;
		this.answer = answer;
	}
}
