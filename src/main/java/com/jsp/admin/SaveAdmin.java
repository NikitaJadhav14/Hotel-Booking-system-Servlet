package com.jsp.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Admin;
import com.jsp.service.AdminService;

@WebServlet("/SaveAdmin")
public class SaveAdmin extends HttpServlet {

	AdminService adminService = new AdminService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin admin = new Admin();
		String name = req.getParameter("admin_name");
		String email = req.getParameter("admin_email");
		String password = req.getParameter("password");

		if (!(name.isBlank() || email.isBlank() || password.isBlank())) {

			admin.setAdmin_name(name);
			admin.setAdmin_email(email);
			admin.setPassword(password);

			if (adminService.validate(admin) && adminService.validatName(admin)) {
				adminService.create(admin);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Account Created Successfully" + " </h1></body></html>");
				printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(
						"<html><body><h1>" + "Email ID Already Exist / Write Proper Name" + " </h1></body></html>");
				printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Give Proper Input" + " </h1></body></html>");
			printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
		}

	}
}
