package team.last.project.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.last.project.dto.ReserveDto;

@Entity
@Data
@NamedEntityGraph(name = "Room_id", attributeNodes = @NamedAttributeNode("room"))
@NamedEntityGraph(name = "ReserveWithPay", attributeNodes = @NamedAttributeNode("pay"))
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVE_ID_SEQ")
	@SequenceGenerator(sequenceName = "reserve_id_seq", allocationSize = 1, name = "RESERVE_ID_SEQ")
	private Long id;
	private Timestamp start_time;
	private Timestamp end_time;
	private int totalprice;
	private String options;
	private int person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_id")
	private Pay pay;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	public static Reserve createReserve(ReserveDto reserveDto, Member member, Room room) {
		Reserve reserve = new Reserve();
		reserve.setStart_time(reserveDto.getStart_time());
		reserve.setEnd_time(reserveDto.getEnd_time());
		reserve.setTotalprice(reserveDto.getTotalprice());
		reserve.setOptions(reserveDto.getOptions());
		reserve.setPerson(reserveDto.getPerson());
		reserve.setMember(member);
		reserve.setRoom(room);
		return reserve;
	}
	public void addPay(Pay pay) {
		this.pay = pay;
	}
}
