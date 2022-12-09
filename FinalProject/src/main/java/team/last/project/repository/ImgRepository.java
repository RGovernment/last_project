package team.last.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Img;
@Repository
public interface ImgRepository extends JpaRepository<Img, Integer>{
	
	List<Img> getAllByReviewId(int reviewid);
}
