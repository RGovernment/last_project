package team.last.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import lombok.Data;


@Entity
@Data
@NamedEntityGraph(name = "RoomwithRoomtype", attributeNodes = @NamedAttributeNode("roomtype"))
public class Room {
	@Id
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_type_id")
	private Roomtype roomtype;
	
	private String name;
	
	private String note;
	
	public void name_update(String name) {
		this.name = name;
	}
	
	public void note_update(String note) {
		this.note = note;
	}
	
	
	
	
}
