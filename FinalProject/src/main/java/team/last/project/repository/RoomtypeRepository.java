package team.last.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Roomtype;

@Repository
public interface RoomtypeRepository extends JpaRepository<Roomtype, Integer> {

}
