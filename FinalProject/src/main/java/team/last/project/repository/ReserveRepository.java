package team.last.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

	@EntityGraph("Room_id")
	List<Reserve> findALLByMemberId(Long id);

	@Query(value = "select r from Reserve r where to_char(r.start_time,'MM') = ?1")
	List<Reserve> findschedule(String month);

}
