package team.last.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public Review reviewView(Integer id) {
	return reviewRepository.findById(id).get();
}
public void reviewDelete(Integer id) {
	reviewRepository.deleteById(id);
}
@Transactional
public Integer reviewUpdate(Integer id, final ReviewDto params) {
	Review review = reviewRepository.findById(id).orElseThrow();
	review.update(params.getTitle(), params.getContent());
	return id;
}
public Page<Review> reviewSearchList(String searchKeyword, Pageable pageable) {
	return reviewRepository.findByTitleContaining(searchKeyword, pageable);
}
}
