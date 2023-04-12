package com.jsp.service;

import java.util.ArrayList;
import java.util.List;

import com.jsp.dao.PropertyDao;
import com.jsp.dao.RoomDao;
import com.jsp.dto.LandLord;
import com.jsp.dto.Property;
import com.jsp.dto.Room;

public class PropertyService {
	PropertyDao propertyDao = new PropertyDao();
	RoomService roomService = new RoomService();

	public void create(Property property) {
		propertyDao.create(property);
	}

	public void update(Property property) {
		propertyDao.update(property);
	}

	public void delete(int id) {
		Property property2 = propertyDao.readPropertyById(id);
		for (Room room : property2.getRooms()) {
			roomService.deleteById(room.getRoom_id());
		}
		propertyDao.delete(id);
	}

	public Property readById(int id) {
		return propertyDao.readPropertyById(id);
	}

	public List<Property> readAllProperty() {
		return propertyDao.readAllProperty();
	}

	public void addRoomsByPropertyId(int id, Property property) {
		Property property2 = propertyDao.readPropertyById(property.getProperty_id());
		if (property2.getLandLord() != null) {
			Room room = roomService.readById(id);
			room.setProperty(property);

			ArrayList<Room> rooms = new ArrayList<Room>();
			rooms.add(room);

			property.setRooms(rooms);

			propertyDao.update(property);
			roomService.update(room);
		} else {
			System.out.println("landlord is not approved");
		}
	}

	public List<Room> readAllRoomsByPropertyId(int id) {
		return roomService.readAllRoomsByPropertyId(id);
	}

	public Property readByName(Property property) {
		if (property.getProperty_name() != null) {
			return propertyDao.readByName(property);
		}
		return null;
	}
}
