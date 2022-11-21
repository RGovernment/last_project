package team.last.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.OptionDto;
import team.last.project.entity.Option;
import team.last.project.repository.OptionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OptionService {
	private final OptionRepository optionRepository;

	public Option optionAdd(Option option) {
		validateDuplicateoption(option);
		return optionRepository.save(option);
	}

	public Option optionget(Integer id) {
		return optionRepository.findById(id).get();
	}
	public OptionDto optionDtoget(Integer id) {
		Option option = optionRepository.findById(id).get();
		return option.convertOptionDto();
	}

	public List<Option> optionList() {
		return optionRepository.findAll();
	}

	public void optionDelete(Integer id) {
		optionRepository.deleteById(id);
	}

	public void optionUpdate(Integer id, final OptionDto optionDto) {
		Option option = optionRepository.findById(id).orElseThrow();
		option.update(optionDto.getName(), optionDto.getType(), optionDto.getNote());
	}

	private void validateDuplicateoption(Option option) {
		Option findoption = optionRepository.findByName(option.getName()).orElse(null);
		if (findoption != null) {
			throw new IllegalStateException("이미 있는 옵션입니다.");
		}
	}

}
