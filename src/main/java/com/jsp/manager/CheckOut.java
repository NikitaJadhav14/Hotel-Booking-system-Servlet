package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.dto.Room;
import com.jsp.service.CustomerService;
import com.jsp.service.RoomService;

@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int roomid = Integer.parseInt(req.getParameter("roomid"));
		int customerid = Integer.parseInt(req.getParameter("customerid"));

		RoomService roomService = new RoomService();
		CustomerService customerService = new CustomerService();

		Customer customer = roomService.readCustomerByRoomId(roomid);
		Room room = roomService.readById(roomid);

		if (room != null && customer != null && customer.getCustomer_id() == customerid) {
			List<Room> rooms = new ArrayList<Room>();
			rooms.add(room);

			if (customerService.checkOut(customerid, rooms)) {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<h1>Checked Out Successfully</h1>");
				printWriter
						.write("<a href= 'http://localhost:8080/Hotel_Project/ManageRoom.html'>Go to Manage Rooms</a>");
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<h1>Give Proper Id</h1>");
				printWriter
						.write("<a href= 'http://localhost:8080/Hotel_Project/ManageRoom.html'>Go to Manage Rooms</a>");
			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<h1>Give Proper Id</h1>");

		}

	}
}
