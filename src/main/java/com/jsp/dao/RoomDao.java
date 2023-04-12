package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.dto.LandLord;
import com.jsp.dto.Room;

public class RoomDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Nikita");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void create(Room room) {
		entityTransaction.begin();
		entityManager.persist(room);
		entityTransaction.commit();
	}

	public Room readById(int id) {
		return entityManager.find(Room.class, id);
	}

	public void delete(int id) {
		Room room2 = entityManager.find(Room.class, id);
		if (room2 != null) {
			entityTransaction.begin();
			entityManager.remove(room2);
			entityTransaction.commit();
		}
	}

	public void update(Room room) {
		Room room2 = entityManager.find(Room.class, room.getRoom_id());
		if (room2 != null) {
			entityTransaction.begin();
			if (room.getRoom_price() != null) {
				room2.setRoom_price(room.getRoom_price());
			}
			if (room.getRoom_status() != null) {
				room2.setRoom_status(room.getRoom_status());
			}
			room2.setCustomer(room.getCustomer());
		}
		entityManager.merge(room2);
		entityTransaction.commit();
	}

	public List<Room> readAllRooms() {
		String sql = "select s from Room s";
		Query query = entityManager.createQuery(sql);
		List<Room> rooms = query.getResultList();
		return rooms;
	}
}
