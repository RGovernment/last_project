package team.last.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import team.last.project.dto.OptPriceDto;

@Entity
@Data
@Table(name="option_price")
@NamedEntityGraph(name = "priceAllMenu", attributeNodes = @NamedAttributeNode("option"))
public class OptPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPTION_PRICE_SEQ")
	@SequenceGenerator(sequenceName = "option_price_seq", allocationSize = 1, name = "OPTION_PRICE_SEQ")
	private int id;
	private String content;
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="option_id")
	private Option option;

	public static OptPrice createOptPrice(OptPriceDto optPriceDto,Option oboption) {
		OptPrice option = new OptPrice();
		option.setContent(optPriceDto.getContent());
		option.setPrice(optPriceDto.getPrice());
		option.setOption(oboption);
		return option;
	}

	public void update(String content, int price) {
		this.content = content;
		this.price = price;
	}
}
