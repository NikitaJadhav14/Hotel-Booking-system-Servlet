package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Room;
import com.jsp.service.CustomerService;
import com.jsp.service.RoomService;

@WebServlet("/checkin")
public class CheckIn extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomService roomService = new RoomService();
		HttpSession httpSession = req.getSession();
		int customerid = (Integer) httpSession.getAttribute("customerID");

		int roomid = Integer.parseInt(req.getParameter("room_id"));
		Room room = roomService.readById(roomid);

		CustomerService customerService = new CustomerService();
		if (customerService.checkIn(customerid, room)) {
			PrintWriter printWriter = resp.getWriter();
			printWriter.println("<h1>Booked Successfully</h1>");
			printWriter.println(
					"<form action='Customerlogin.jsp' method='post'><input type='submit' value='Go to Customer Home'></form>");

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.println("<h1>Room is Already Booked</h1>");
			printWriter.println("<h1>Go back and try Again </h1>");
			printWriter.println("<h1>or else</h1>");
			printWriter.println(
					"<form action='Customerlogin.jsp' method='post'><input type='submit' value='Go to Customer Home'></form>");
		}

	}
}
