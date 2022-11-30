package team.last.project.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import team.last.project.dto.PayDto;


@Entity
@Data
public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAY_SEQ")
	@SequenceGenerator(sequenceName = "pay_seq", allocationSize = 1, name = "PAY_SEQ")
	private int id;
	private Timestamp pay_date_time;
	private int pay_amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;

	public static Pay Pay_success(PayDto paydto,Member member) {
		Pay pay = new Pay();
		pay.setPay_amount(paydto.getPay_amount());
		pay.setPay_date_time(paydto.getPay_date_time());
		pay.setMember(member);
		return pay;
		
	}
	
}


