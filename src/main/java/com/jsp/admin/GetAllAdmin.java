package com.jsp.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Admin;
import com.jsp.service.AdminService;

@WebServlet("/admin input")

public class GetAllAdmin extends HttpServlet {
	AdminService adminService=new AdminService();
	 public List<Admin> admins=  adminService.readAllAdmin();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminService adminService=new AdminService();
		adminService.readAllAdmin();
	
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("GetAllAdmin.jsp");
		requestDispatcher.forward(req, resp);
		
	
}
}
