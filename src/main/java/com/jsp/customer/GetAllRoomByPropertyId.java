package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jsp.dto.Room;
import com.jsp.service.RoomService;

@WebServlet("/CustomerHotelView")
public class GetAllRoomByPropertyId extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomService roomService = new RoomService();
		int id = Integer.parseInt(req.getParameter("property_id"));

		List<Room> rooms = roomService.readAllRoomsByPropertyId(id);
		if (!(rooms.isEmpty())) {
			PrintWriter printWriter = resp.getWriter();
			printWriter.println(
					"<html><body><br><br><br><table border='1' style='border-collapse: collapse;'><tr><th>Id</th><th>Price</th><th>Status</th></tr>");
			for (Room room : rooms) {
				printWriter.println("<tr><td>" + room.getRoom_id() + "</td><td>" + room.getRoom_price() + "</td><td>"
						+ room.getRoom_status() + "</td></tr>");
			}
			printWriter.println(
					"</table><br><br><br><form action='checkin' method='post'> Enter Room Id:<input type='number' min='1' name='room_id'> <input type='submit' value='Book Room'></form></body></html>");

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<h1>Invalid ID</h1>");
		}
	}
}
