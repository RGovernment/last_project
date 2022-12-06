package team.last.project.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Pay;
import team.last.project.repository.PayRepository;

@Service
@RequiredArgsConstructor
public class PayService {

	private final PayRepository payRepository;
	
	public Pay savepay(Pay pay) {
		return payRepository.save(pay);
	}

	public void deleteTrade(String tid) {
		Pay pay = payRepository.findByTid(tid).orElse(null);
		payRepository.delete(pay);
	}

	public Pay get(int payid) {
		return payRepository.findById(payid).orElse(null);
	}

	public void delete(Pay pay) {
		payRepository.delete(pay);
	}

	
}
