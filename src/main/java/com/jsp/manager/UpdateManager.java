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
import com.jsp.service.LandlordService;

@WebServlet("/UpdateManager")
public class UpdateManager extends HttpServlet {
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LandLord landLord = new LandLord();
		HttpSession httpSession = req.getSession();

		int id = (Integer) httpSession.getAttribute("ID");

		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String address = req.getParameter("address");

		if (!(name.isBlank() || username.isBlank() || address.isBlank())) {
			landLord.setId(id);
			landLord.setName(name);
			landLord.setAddress(address);
			landLord.setUsername(username);

			if (landlordService.validatName(landLord) && landlordService.validate(landLord)) {
				landlordService.update(landLord);

				httpSession.setAttribute("name", landLord.getName());
				httpSession.setAttribute("username", landLord.getUsername());
				httpSession.setAttribute("address", landLord.getAddress());

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Updated Successfully </h1></body></html>");
				printWriter.write("<form action=\"manager.jsp\" method=\"post\">\r\n"
						+ "    <input type=\"submit\" value=\"Go To Manager Home\" />\r\n" + "</form>");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Give Proper Input" + " </h1></body></html>");
			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Give Proper Input" + " </h1></body></html>");

		}

	}
}