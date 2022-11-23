package team.last.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.OptPrice;

public interface OptPriceRepository extends JpaRepository<OptPrice, Integer> {
	Optional<OptPrice> findByContent(String content);

	List<OptPrice> findALLByOptionId(Integer id);

	@EntityGraph("priceAllMenu")
	List<OptPrice> findAll();
}
