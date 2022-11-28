package team.last.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.OptPrice;
import team.last.project.entity.Reserve;
public interface ReserveRepository extends JpaRepository<Reserve, Integer>{

	List<Reserve> findALLByMemberId(Integer id);

}
