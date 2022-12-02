package team.last.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.Option;

public interface OptionRepository extends JpaRepository<Option, Integer> {
	Optional<Option> findByName(String name);

	@EntityGraph("OptionwithPrice")
	List<Option> findAll();
}
