package team.last.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import team.last.project.constant.Option_type;
import team.last.project.dto.OptionDto;

@Entity
@Data
@Table(name = "option_table")
@NamedEntityGraph(name = "OptionwithPrice", attributeNodes = @NamedAttributeNode("optPrices"))
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPTION_TABLE_SEQ")
	@SequenceGenerator(sequenceName = "option_table_seq", allocationSize = 1, name = "OPTION_TABLE_SEQ")
	@Column(name = "option_id")
	private int id;
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private Option_type type;
	private String note;

	@OneToMany(mappedBy = "option")
	private List<OptPrice> optPrices = new ArrayList<>();

	public static Option createOption(OptionDto optionDto) {
		Option option = new Option();
		option.setName(optionDto.getName());
		option.setType(optionDto.getType());
		option.setNote(optionDto.getNote());
		return option;
	}

	public OptionDto convertOptionDto() {
		OptionDto optionDto = new OptionDto();
		optionDto.setName(name);
		optionDto.setNote(note);
		optionDto.setType(type);
		return optionDto;
	}

	public void update(String name, Option_type type, String note) {
		this.name = name;
		this.type = type;
		this.note = note;
	}
}
