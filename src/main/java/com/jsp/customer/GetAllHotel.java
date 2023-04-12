package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Property;
import com.jsp.service.PropertyService;

@WebServlet("/getAllHotel")
public class GetAllHotel extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PropertyService propertyService = new PropertyService();
		List<Property> properties = propertyService.readAllProperty();

		PrintWriter printWriter = resp.getWriter();
		printWriter.println("<html><body><table border=\"1\" style=\"border-collapse: collapse;\">\r\n"
				+ "			<tr>\r\n" + "				<th>Id</th>\r\n" + "				<th>Name</th>\r\n"
				+ "				<th>Location</th>\r\n" + "				<th>Rating</th>\r\n"
				+ "				<th>Num of Rooms</th>\r\n" + "			</tr>");
		for (Property property : properties) {
			printWriter.println("<tr>\r\n" + "				<td>" + property.getProperty_id() + "</td>\r\n"
					+ "				<td>" + property.getProperty_name() + "</td>\r\n" + "				<td>"
					+ property.getProperty_location() + "</td>\r\n" + "				<td>" + property.getRating()
					+ "</td>\r\n" + "				<td>" + property.getNum_of_rooms() + "</td>\r\n"
					+ "			</tr>");
		}
		printWriter.println("</table>	<br>\r\n" + "		<br>\r\n"
				+ "		<form action=\"CustomerHotelView\" method=\"post\">\r\n"
				+ "			Enter Hotel Id:<input type=\"number\" min=\"1\" name=\"property_id\">\r\n"
				+ "			<input type=\"submit\" value=\"View Room\">\r\n" + "		</form></body></html>");
	}

}