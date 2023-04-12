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

import com.jsp.dto.Room;
import com.jsp.service.LandlordService;
import com.jsp.service.RoomService;

@WebServlet("/DeleteRoom")

public class DeleteRoom extends HttpServlet {
	RoomService roomService = new RoomService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int roomid = Integer.parseInt(req.getParameter("id"));
			Room room = roomService.readById(roomid);
			HttpSession httpSession = req.getSession();
			int propertyid = (Integer) httpSession.getAttribute("propertyID");

			if (room.getProperty().getProperty_id() == propertyid) {
				roomService.deleteById(roomid);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Deleted Successfully </h1></body></html>");
				printWriter
						.write("<a href= 'http://localhost:8080/Hotel_Project/ManageRoom.html'>Go to Manage Rooms</a>");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Room Id Does Not Exist In this Hotel </h1></body></html>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1> Give a Valid Room Id</h1></body></html>");
		}

	}
}
