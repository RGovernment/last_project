package team.last.project.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class KakaoPayApprovalDto {

	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private AmountDto amount;
	private CardDto card_info;
	private String item_name, item_code, payload;
	private Integer quantity, tax_free_amount, vat_amount;
	private Timestamp created_at, approved_at;
}


