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
import com.jsp.dto.Property;
import com.jsp.service.LandlordService;
import com.jsp.service.PropertyService;

@WebServlet("/SaveHotel")
public class SaveHotel extends HttpServlet {
	PropertyService propertyService = new PropertyService();
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		try {
			LandLord landLord = landlordService.readById((Integer) httpSession.getAttribute("ID"));

			String name = req.getParameter("property_name");
			String loc = req.getParameter("property_location");
			String noOfRoom = req.getParameter("num_of_rooms");
			String rating = req.getParameter("rating");

			if (!(name.isBlank() || loc.isBlank() || noOfRoom.isBlank() || rating.isBlank())) {
				Property property = new Property();
				property.setProperty_name(name);
				property.setProperty_location(loc);
				property.setNum_of_rooms(Integer.parseInt(noOfRoom));
				property.setRating(Integer.parseInt(rating));
				property.setLandLord(landLord);
				propertyService.create(property);

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Hotel Added Successfully </h1></body></html>");
				printWriter.write(
						"<a href='http://localhost:8080/Hotel_Project/LoginProperty.html'>Go to Manage Hotel</a>");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Feild Cannot Be Empty </h1></body></html>");

			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1> Invalid Input</h1></body></html>");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
