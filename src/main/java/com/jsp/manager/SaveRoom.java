package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Property;
import com.jsp.dto.Room;
import com.jsp.service.PropertyService;
import com.jsp.service.RoomService;

@WebServlet("/SaveRoom")
public class SaveRoom extends HttpServlet {
	RoomService roomService = new RoomService();
	PropertyService propertyService = new PropertyService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String roomPrice = req.getParameter("room_price");
		String roomStatus = req.getParameter("room_status");

		if (!(roomPrice.isBlank() || roomStatus.isBlank())) {
			int propertyid = (Integer) httpSession.getAttribute("propertyID");
			Property property = propertyService.readById(propertyid);

			Room room = new Room();
			room.setRoom_price(Double.parseDouble(roomPrice));
			room.setRoom_status(roomStatus);
			room.setProperty(property);

			roomService.create(room);

			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>  Room Added Successfully </h1></body></html>");
			printWriter.write("<a href= 'http://localhost:8080/Hotel_Project/ManageRoom.html'>Go to Manage Rooms</a>");

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>  Feild Cannot be Blank</h1></body></html>");
		}

	}

}
