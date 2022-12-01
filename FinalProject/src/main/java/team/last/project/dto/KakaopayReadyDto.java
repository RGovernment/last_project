package team.last.project.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class KakaopayReadyDto {

	private String tid, next_redirect_pc_url;
	private Date created_at;
	
}
