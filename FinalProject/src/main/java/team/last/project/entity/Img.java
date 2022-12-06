package team.last.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "img")
public class Img {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMG_SEQ")
	@SequenceGenerator(sequenceName = "img_seq", allocationSize = 1, name = "IMG_SEQ")
	private int id;

	private String name;

	private String uuid;

	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;

	public static Img createImg(String name, String uuid, String path, Review review) {
		Img img = new Img();
		img.setName(name);
		img.setUuid(uuid);
		img.setPath(path);
		img.setReview(review);
		return img;
	}

}
