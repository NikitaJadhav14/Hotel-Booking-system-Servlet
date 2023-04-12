package com.jsp.manager;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.LandLord;
import com.jsp.service.LandlordService;

@WebServlet("/SaveManager")
public class SaveManager extends HttpServlet {
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LandLord landLord = new LandLord();

		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		if (!(name.isBlank() || username.isBlank() || address.isBlank() || password.isBlank())) {
			landLord.setName(name);
			landLord.setUsername(username);
			landLord.setAddress(address);
			landLord.setPassword(password);

			if (landlordService.validate(landLord) && landlordService.validatName(landLord)) {
				landlordService.create(landLord);
				PrintWriter printWriter1 = resp.getWriter();
				printWriter1.write("<html><body><h1>" + "Account Created Successfully" + " </h1></body></html>");
				printWriter1.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
			} else {
				PrintWriter printWriter1 = resp.getWriter();
				printWriter1.write(
						"<html><body><h1>" + "Username Already Present / Write Proper Name" + " </h1></body></html>");
				printWriter1.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");

			}

		} else {
			PrintWriter printWriter1 = resp.getWriter();

			printWriter1.write("<html><body><h1>" + "Feild Cannot be empty" + " </h1></body></html>");
			printWriter1.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
		}

	}
}