package com.jsp.admin;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Admin;
import com.jsp.service.AdminService;

@WebServlet("/GetAdminById")

public class GetAdminById extends HttpServlet {
	AdminService adminService = new AdminService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("admin_id"));

		Admin a1 = adminService.readById(id);
		if (a1 != null) {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h4>Id:" + a1.getAdmin_id() + "</h4></body></html>");
			printWriter.write("<html><body><h4>Name: " + a1.getAdmin_name() + "</h4></body></html>");
			printWriter.write("<html><body> <h4>Email:" + a1.getAdmin_email() + "</h4></body></html> ");

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>Invalid ID</h1></body></html>");

		}
	}
}
