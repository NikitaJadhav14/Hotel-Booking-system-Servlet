package com.jsp.service;

import java.util.ArrayList;
import java.util.List;

import com.jsp.dao.RoomDao;
import com.jsp.dto.Customer;
import com.jsp.dto.Room;

public class RoomService {
	RoomDao roomDao = new RoomDao();

	public void create(Room room) {
		room.setRoom_status("AVAILABLE");
		roomDao.create(room);
	}

	public void update(Room room) {
		roomDao.update(room);
	}

	public void deleteById(int id) {
		roomDao.delete(id);
	}

	public Room readById(int id) {

		return roomDao.readById(id);
	}

	public List<Room> readAllRoomsByPropertyId(int id) {

		List<Room> rooms = roomDao.readAllRooms();
		List<Room> roomsAl = new ArrayList<Room>();

		for (Room room : rooms) {
			if (room.getProperty().getProperty_id() == id) {
				roomsAl.add(room);
			}
		}
		return roomsAl;

	}

	public List<Room> readAllRooms() {
		return roomDao.readAllRooms();

	}

	public Customer readCustomerByRoomId(int id) {
		Room room = roomDao.readById(id);
		return room.getCustomer();
	}
}
