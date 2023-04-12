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

@WebServlet("/DeleteAdmin")
public class DeleteAdmin extends HttpServlet {

	AdminService adminService = new AdminService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		int id = (Integer) httpSession.getAttribute("ID");

		Admin admin = new Admin();
		admin.setAdmin_email((String) httpSession.getAttribute("email"));
		admin.setPassword(req.getParameter("password"));

		if (adminService.logIn(admin)) {
			adminService.delete(id);
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
