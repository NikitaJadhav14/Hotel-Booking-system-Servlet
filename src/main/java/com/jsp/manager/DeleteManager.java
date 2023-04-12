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
import com.jsp.service.LandlordService;

@WebServlet("/DeleteManager")

public class DeleteManager extends HttpServlet {
	LandlordService landlordService = new LandlordService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		int id = (Integer) httpSession.getAttribute("ID");
		LandLord landLord = new LandLord();
		landLord.setUsername((String) httpSession.getAttribute("username"));
		landLord.setPassword(req.getParameter("password"));

		if (landlordService.logIn(landLord)) {
			landlordService.delete(id);
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Deleted Successfully" + " </h1></body></html>");
			httpSession.invalidate();
			printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<h1>Incorrect Password</h1>");
		}

	}
}
