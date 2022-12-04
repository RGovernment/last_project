package team.last.project.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import team.last.project.dto.ReviewDto;

@Entity
@Data
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ")
    @SequenceGenerator(sequenceName = "review_seq", allocationSize = 1, name = "REVIEW_SEQ")
	private int id;
	private String title;
	private String content;
	private int score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;
	
	@OneToOne()
	@JoinColumn(name = "reserve_id")
	private Reserve reserve;
	
	@Column(columnDefinition = "timestamp default current_timestamp")
	private Timestamp cdate;
	
	public static Review createReview(ReviewDto reviewDto,Member member,Room room) {
		Review review = new Review();
		review.setId(reviewDto.getId());
		review.setTitle(reviewDto.getTitle());
		review.setContent(reviewDto.getContent());
		review.setScore(reviewDto.getScore());
		review.setMember(member);
		review.setRoom(room);
		return review;
	}
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
