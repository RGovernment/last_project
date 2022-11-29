package team.last.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Page <Review> findByTitleContaining(String searchKeyword, Pageable pageable); 
}
