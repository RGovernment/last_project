package team.last.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Room;
import team.last.project.entity.Roomtype;
import team.last.project.repository.RoomRepository;
import team.last.project.repository.RoomtypeRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
	private final RoomRepository roomRepository;
	private final RoomtypeRepository roomtypeRepository;
	
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

	public void name_update(int id, String name) {
		// id를 찾아서 Room Entity와 연동
		Room room = roomRepository.findById(id).get();
		room.name_update(name);
	}

	public void note_update(int id, String note) {
		// id를 찾아서 Room Entity와 연동
		Room room = roomRepository.findById(id).get();
		room.note_update(note);
	}
	
	public void room_price_update(int id, String price_num , int price) {
		Room room = roomRepository.findById(id).get();
		Roomtype roomtype = room.getRoomtype();
		roomtype.price_update(price_num, price);
	}
	
	public List<Roomtype> roomtypelist(){
		return roomtypeRepository.findAll();
	}
}
