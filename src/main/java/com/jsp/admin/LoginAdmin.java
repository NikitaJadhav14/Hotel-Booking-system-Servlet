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

@WebServlet("/login")
public class LoginAdmin extends HttpServlet {
	AdminService adminService = new AdminService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin admin = new Admin();

		String username = req.getParameter("admin_email");
		String password = req.getParameter("password");

		if (!(username.isBlank() || password.isBlank())) {
			admin.setAdmin_email(username);
			admin.setPassword(password);

			Admin admin2 = adminService.getByEmailId(admin);
			if (admin2 != null) {
				if (adminService.logIn(admin)) {
					HttpSession httpSession = req.getSession();
					httpSession.invalidate();
					HttpSession httpSession1 = req.getSession();
					httpSession1.setAttribute("ID", admin2.getAdmin_id());
					httpSession1.setAttribute("name", admin2.getAdmin_name());
					httpSession1.setAttribute("email", admin2.getAdmin_email());

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.jsp");
					requestDispatcher.forward(req, resp);
				} else {
					PrintWriter printWriter = resp.getWriter();
					printWriter.write("<h1>Incorrect Email Or Password</h1>");
				}
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<h1>Incorrect Email Or Password</h1>");
			}
		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Feild Cannot be empty" + " </h1></body></html>");
			printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
		}

	}
}
