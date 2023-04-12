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

import com.jsp.dto.LandLord;
import com.jsp.dto.Property;
import com.jsp.service.LandlordService;
import com.jsp.service.PropertyService;

@WebServlet("/ExistingProperty")
public class ExistingProperty extends HttpServlet {
	PropertyService propertyService = new PropertyService();
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		try {
			LandLord landLord = landlordService.readById((Integer) httpSession.getAttribute("ID"));

			int id = Integer.parseInt(req.getParameter("property_id"));
			Property property2 = propertyService.readById(id);
			if (property2 != null) {

				if (property2.getLandLord().getId() == landLord.getId()) {
					httpSession.setAttribute("propertyID", id);

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("ManageProperty.html");
					requestDispatcher.forward(req, resp);
				} else {
					PrintWriter printWriter = resp.getWriter();
					printWriter.write("<html><body><h1> Hotel Id Does NOt Belongs To You  </h1></body></html>");

				}
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Invalid Property Id</h1></body></html>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
