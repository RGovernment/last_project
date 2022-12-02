package team.last.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team.last.project.entity.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer>{

	Optional<Pay> findByTid(String tid);

}
