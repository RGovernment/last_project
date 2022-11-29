package team.last.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long>{

	@EntityGraph("Room_id")
	List<Reserve> findALLByMemberId(Long id);

}
