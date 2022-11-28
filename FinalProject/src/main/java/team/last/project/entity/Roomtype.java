package team.last.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "room_type")
public class Roomtype {
	@Id
	private int id;

	private String name;

	//3h 평일
	private int price1;
	//6h 평일
	private int price2;
	//올나잇 평일
	private int price3;
	//3h 주말
	private int price4;
	//6h 주말
	private int price5;
	//올나잇 주말
	private int price6;

	public void update(List<Integer> prices) {
		this.price1 = prices.get(0);
		this.price2 = prices.get(1);
		this.price3 = prices.get(2);
		this.price4 = prices.get(3);
		this.price5 = prices.get(4);
		this.price6 = prices.get(5);
	}
}
