package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.LandLord;
import com.jsp.dto.Room;
import com.jsp.service.LandlordService;
import com.jsp.service.RoomService;

@WebServlet("/GetRoomById")
public class GetRoomById extends HttpServlet {
	RoomService roomService = new RoomService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int roomid = Integer.parseInt(req.getParameter("id"));
			Room room = roomService.readById(roomid);
			HttpSession httpSession = req.getSession();
			int propertyid = (Integer) httpSession.getAttribute("propertyID");

			if (room.getProperty().getProperty_id() == propertyid) {
				Room r = roomService.readById(roomid);

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h4>Id:" + r.getRoom_id() + "</h4></body></html>");
				printWriter.write("<html><body><h4>Name: " + r.getRoom_price() + "</h4></body></html>");
				printWriter.write("<html><body> <h4>Address:" + r.getRoom_status() + "</h4></body></html> ");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Room Id Does Not Exist In this Hotel </h1></body></html>");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>Give Proper Input</h1></body></html>");

		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>Id does not Exist</h1></body></html>");

		}
	}
}
