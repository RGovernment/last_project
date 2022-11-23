package team.last.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.OptPriceDto;
import team.last.project.entity.OptPrice;
import team.last.project.repository.OptPriceRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OptPriceService {
	private final OptPriceRepository optPriceRepository;

	public OptPrice optPriceAdd(OptPrice optPrice) {
		validateDuplicateoptPrice(optPrice);
		return optPriceRepository.save(optPrice);
	}

	public OptPrice optPriceget(Integer id) {
		return optPriceRepository.findById(id).get();
	}

	public List<OptPrice> optPriceListget(Integer id) {
		List<OptPrice> optpricelist = optPriceRepository.findALLByOptionId(id);
		return optpricelist;
	}

	public List<OptPrice> optPriceAllList(){
		
		
		return optPriceRepository.findAll();
	}
	
	public void optPriceDelete(Integer id) {
		optPriceRepository.deleteById(id);
	}

	public void optPriceUpdate(Integer id, final OptPriceDto optPriceDto) {
		OptPrice optPrice = optPriceRepository.findById(id).orElseThrow();
		optPrice.update(optPriceDto.getContent(), optPriceDto.getPrice());
	}

	private void validateDuplicateoptPrice(OptPrice optPrice) {
		OptPrice findoptPrice = optPriceRepository.findByContent(optPrice.getContent()).orElse(null);
		if (findoptPrice != null) {
			throw new IllegalStateException("이미 있는 옵션입니다.");
		}
	}

}
