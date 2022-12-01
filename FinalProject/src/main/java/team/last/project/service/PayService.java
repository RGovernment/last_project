package team.last.project.service;

import org.springframework.stereotype.Service;

import team.last.project.entity.Pay;
import team.last.project.repository.PayRepository;

@Service
public class PayService {

	private PayRepository payRepository;
	
	public void savepay(Pay pay) {
		payRepository.save(pay);
	}

	
}
