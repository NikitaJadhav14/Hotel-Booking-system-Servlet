package com.jsp.service;

import java.util.ArrayList;
import java.util.List;

import com.jsp.dao.CustomerDao;
import com.jsp.dto.Admin;
import com.jsp.dto.Customer;
import com.jsp.dto.Room;

public class CustomerService {
	CustomerDao customerDao = new CustomerDao();
	RoomService roomService = new RoomService();

	public void create(Customer customer) {
		customerDao.create(customer);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public void delete(int id) {
		try {
			List<Room> rooms = customerDao.readById(id).getRooms();
			for (Room room : rooms) {
				roomService.deleteById(room.getRoom_id());
			}
			customerDao.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Customer readById(int id) {
		return customerDao.readById(id);
	}

	public List<Customer> readAllCustomer() {
		return customerDao.readAllCustomers();
	}

	public boolean logIn(Customer customer) {
		if (customerDao.logIn(customer) == null) {
			return false;
		} else
			return true;
	}

	public boolean checkIn(int customer_id, Room room) {
		Room room2 = roomService.readById(room.getRoom_id());
		if (room2.getRoom_status().equalsIgnoreCase("BOOKED") && room2.getCustomer() != null) {
			return false;
		}
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(room);

		Customer customer = customerDao.readById(customer_id);
		customer.setRooms(rooms);
		customerDao.update(customer);

		for (Room room1 : rooms) {
			room1.setCustomer(customer);
			room1.setRoom_status("BOOKED");
			roomService.update(room1);
		}
		return true;
	}

	public boolean checkOut(int customer_id, List<Room> rooms) {
		Customer customer = customerDao.readById(customer_id);
		if (customer != null) {
			customer.setRooms(null);
			customerDao.update(customer);

			for (Room room : rooms) {
				room.setCustomer(null);
				room.setRoom_status("AVAILABLE");
				roomService.update(room);
			}
			return true;
		} else {
			return false;
		}

	}

	public boolean validate(Customer customer) {
		Customer customer2 = customerDao.validate(customer);
		if (customer2 != null && customer.getUsername().equals(customer2.getUsername())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validatName(Customer customer) {
		String name = customer.getCustomer_name();
		char[] c = name.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!(c[i] >= 'a' && c[i] <= 'z' || c[i] >= 'A' && c[i] <= 'Z' || c[i] == ' ')) {
				return false;
			}
		}
		return true;
	}

	public Customer getByUserName(Customer customer) {
		return customerDao.getByUserName(customer);
	}
}
