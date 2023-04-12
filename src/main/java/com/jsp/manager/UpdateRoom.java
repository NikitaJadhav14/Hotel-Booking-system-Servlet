package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.LandLord;
import com.jsp.dto.Room;
import com.jsp.service.RoomService;

@WebServlet("/UpdateRoom")
public class UpdateRoom extends HttpServlet {
	RoomService roomService = new RoomService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roomId = req.getParameter("id");
		String roomPrice = req.getParameter("room_price");
		String roomStatus = req.getParameter("room_status");
		try {
			if (!(roomPrice.isBlank() || roomStatus.isBlank() || roomId.isBlank())) {
				Room room = new Room();
				room.setRoom_id(Integer.parseInt(roomId));
				room.setRoom_price(Double.parseDouble(roomPrice));
				room.setRoom_status(roomStatus);
				roomService.update(room);

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Updated Successfully </h1></body></html>");
				printWriter
						.write("<a href= 'http://localhost:8080/Hotel_Project/ManageRoom.html'>Go to Manage Rooms</a>");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>  Feild Cannot be Blank</h1></body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1> Give Proper Input </h1></body></html>");
		}

	}
}
