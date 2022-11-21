package team.last.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.Option;
public interface OptionRepository extends JpaRepository<Option, Integer>{
	Optional<Option> findByName(String name);
}
