package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Property;
import com.jsp.service.PropertyService;

@WebServlet("/UpdateHotel")
public class UpdateHotel extends HttpServlet {
	PropertyService propertyService = new PropertyService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		int id = (Integer) httpSession.getAttribute("propertyID");

		String name = req.getParameter("property_name");
		String loc = req.getParameter("property_location");
		String noOfRoom = req.getParameter("num_of_rooms");
		String rating = req.getParameter("rating");

		if (!(name.isBlank() || loc.isBlank() || noOfRoom.isBlank() || rating.isBlank())) {
			Property property = new Property();
			property.setProperty_id(id);
			property.setProperty_name(name);
			property.setProperty_location(loc);
			property.setNum_of_rooms(Integer.parseInt(noOfRoom));
			property.setRating(Integer.parseInt(rating));
			propertyService.update(property);

			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1> Updated Successfully </h1></body></html>");
			printWriter.write("<form action=\"ManageProperty.html\" method=\"post\">\r\n"
					+ "    <input type=\"submit\" value=\"Go to Manage Hotel\" />\r\n" + "</form>");

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1> Feild Cannot Be Empty </h1></body></html>");

		}

	}
}
