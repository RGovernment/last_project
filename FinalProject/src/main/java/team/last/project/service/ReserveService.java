package team.last.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Reserve;
import team.last.project.repository.ReserveRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReserveService {
	private final ReserveRepository reserveRepository;
	
	public void reserve(Reserve reserve) {
		reserveRepository.save(reserve);
	}
	//요청한 달의 데이터 select
	public List<Reserve> findschedule(String month){
		return reserveRepository.findschedule(month);
	}
	
	public List<Reserve> reserveList(Long id) {
		List<Reserve> reserve = reserveRepository.findALLByMemberId(id);
		return reserve;
	}
	

}
