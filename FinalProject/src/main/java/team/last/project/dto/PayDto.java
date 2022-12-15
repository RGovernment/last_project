package team.last.project.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDto {

	private int pay_amount;
	private Timestamp pay_date_time;
	private String item;
	private String tid;
	private String ordercode;
	
}
