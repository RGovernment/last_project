package team.last.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.AskBoard;

@Repository
public interface AskBoardRepository extends JpaRepository<AskBoard, Integer> {
	Page<AskBoard> findALLByMemberId(Long id, Pageable pageable);

	@EntityGraph("AskBoardWithMember")
	Page<AskBoard> findAll(Pageable pageable);
	
	@EntityGraph("AskBoardWithMember")
	Optional<AskBoard> findById(Integer id);
}