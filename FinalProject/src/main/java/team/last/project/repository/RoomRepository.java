package team.last.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.last.project.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	@EntityGraph("RoomwitRoomtype")
	public Optional<Room> findById(int id);

}
