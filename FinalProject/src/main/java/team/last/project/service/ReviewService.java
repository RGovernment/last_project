package team.last.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.ReviewDto;
import team.last.project.entity.Review;
import team.last.project.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public void write(Review review) {
		reviewRepository.save(review);
	}

	public Page<Review> reviewList(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}
	//방 별 리뷰 가져오기 2022.12.07
	public Page<Review> reviewList(int roomid, Pageable pageable) {
		return reviewRepository.findAllByRoomid(roomid, pageable);
	}
	
	public Review reviewGet(Integer id) {
		return reviewRepository.findById(id).get();
	}

	public void reviewDelete(Integer id) {
		reviewRepository.deleteById(id);
	}

	@Transactional
	public Integer reviewUpdate(Integer id, final ReviewDto params) {
		Review review = reviewRepository.findById(id).orElseThrow();
		review.update(params.getTitle(), params.getContent(), params.getScore());
		return id;
	}

	public Page<Review> reviewSearchList(String searchKeyword, Pageable pageable) {
		return reviewRepository.findByTitleContaining(searchKeyword, pageable);
	}

	public int lastreview() {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");

		List<Review> list = reviewRepository.findAll(sort);

		int lastid = list.get(0).getId();
		return lastid;

	}
	
	public Review reviewByreserveId(Long id) {
		return reviewRepository.findByReserveId(id).orElse(null);
	}
	
	public List<Review> reviewbyroomid(int id) {
		return reviewRepository.findByRoomId(id);
	}



}
