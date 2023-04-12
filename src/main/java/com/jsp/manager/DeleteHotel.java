package com.jsp.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.service.PropertyService;

@WebServlet("/DeleteHotel")
public class DeleteHotel extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PropertyService propertyService = new PropertyService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		int id = (Integer) httpSession.getAttribute("propertyID");

		propertyService.delete(id);

		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<html><body><h1>" + "Deleted Successfully" + " </h1></body></html>");
		printWriter.write("<form action=\"ManageProperty.html\" method=\"post\">\r\n"
				+ "    <input type=\"submit\" value=\"Go to Manage Hotel\" />\r\n" + "</form>");

	}
}
