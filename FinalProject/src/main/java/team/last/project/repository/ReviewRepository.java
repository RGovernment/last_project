package team.last.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Page<Review> findByTitleContaining(String searchKeyword, Pageable pageable);
	
	@Override
	@Query(value = "select r " + "from Review r " + "left join fetch r.room "
			+ "left join fetch r.member ", countQuery = "select count(r.id) from Review r")
	Page<Review> findAll(Pageable pageable);
	
	Optional<Review> findByReserveId(Long id);

	List<Review> findByRoomId(int id);
	
	//방 별 리뷰 데이터 가져오기 2022.12.07
	@Query(value = "select r " + "from Review r " + "left join fetch r.room "
			+ "left join fetch r.member where r.room.id = ?1", countQuery = "select count(r.id) from Review r")
	Page<Review> findAllByRoomid(int roomid, Pageable pageable);
	
}
