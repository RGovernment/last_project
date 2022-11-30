package team.last.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Room;
import team.last.project.repository.RoomRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
	private final RoomRepository roomRepository;

	public List<Room> roomList() {
		return roomRepository.findAll();
	}

	public List<String> roomnameList() {
		List<String> idlist = new ArrayList<>();
		List<Room> id = roomRepository.findAll();
		id.forEach(x -> idlist.add(x.getName()));
		return idlist;

	}

	public Optional<Room> roomget(int id) {
		return roomRepository.findById(id);
	}
}
