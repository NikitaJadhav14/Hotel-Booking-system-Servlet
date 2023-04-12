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

@WebServlet("/Managerlogin")
public class LoginManager extends HttpServlet {
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LandLord landLord = new LandLord();
		
		landLord.setUsername(req.getParameter("username"));
		landLord.setPassword(req.getParameter("password"));

		LandLord landLord2 = landlordService.getByUserName(landLord);

		if (landLord2 != null) {

			if (landlordService.logIn(landLord)) {
				HttpSession httpSession1 = req.getSession();
				httpSession1.invalidate();
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("ID", landLord2.getId());
				httpSession.setAttribute("name", landLord2.getName());
				httpSession.setAttribute("username", landLord2.getUsername());
				httpSession.setAttribute("address", landLord2.getAddress());

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("manager.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<h1>Incorrect Name Or Password</h1>");
			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<h1>Incorrect Email Or Password</h1>");
		}
	}

}