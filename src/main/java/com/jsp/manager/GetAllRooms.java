package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.LandLord;
import com.jsp.dto.Room;
import com.jsp.service.RoomService;

@WebServlet("/GetAllRooms")
public class GetAllRooms extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomService roomService = new RoomService();
		HttpSession httpSession = req.getSession();
		int propertyid = (Integer) httpSession.getAttribute("propertyID");

		List<Room> rooms = roomService.readAllRoomsByPropertyId(propertyid);
		PrintWriter printWriter = resp.getWriter();
		printWriter.println(
				"<html><head><title>All Rooms</title></head><body><table border='1' style='border-collapse: collapse;'><tr><th>Id</th><th>Price</th><th>Status</th><th>customer_id</th><th>property_id</th></tr>");
		for (Room room : rooms) {
			if (room.getCustomer() != null) {
				printWriter.println("<tr><td>" + room.getRoom_id() + "</td><td>" + room.getRoom_price() + "</td><td>"
						+ room.getRoom_status() + "</td><td>" + room.getCustomer().getCustomer_id() + "</td><td>"
						+ room.getProperty().getProperty_id() + "</td></tr>");

			} else {
				printWriter.println("<tr><td>" + room.getRoom_id() + "</td><td>" + room.getRoom_price() + "</td><td>"
						+ room.getRoom_status() + "</td><td>" + "null" + "</td><td>"
						+ room.getProperty().getProperty_id() + "</td></tr>");

			}
		}
		printWriter.println(
				"</table><br><br><br><form action=ManageRoom.html method=post><input type=submit value='Go To Manage Room'></form></body></html>");

	}
}
