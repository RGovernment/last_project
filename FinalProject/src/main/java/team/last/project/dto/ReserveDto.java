package team.last.project.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDto {
	private Timestamp start_time;
	private Timestamp end_time;
	private int totalprice;
	private String options;
	private int person;
}
