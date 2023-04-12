package com.jsp.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.LandLord;
import com.jsp.service.AdminService;

@WebServlet("/readallunapprovedlandlord")
public class ReadAllUnApprovedLandlord extends HttpServlet {
//	AdminService adminService = new AdminService();
//	public List<LandLord> landlords = adminService.readAllUnapprovedLandlords();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminService adminService = new AdminService();
		List<LandLord> landlords = adminService.readAllUnapprovedLandlords();

		PrintWriter printWriter = resp.getWriter();
		printWriter.println(
				"<html><body><table border='1' style='border-collapse: collapse;'><tr><th>Id</th><th>Name</th><th>Address</th><th>Status</th></tr>");
		for (LandLord landLord : landlords) {
			printWriter.println("<tr><td>" + landLord.getId() + "</td><td>" + landLord.getName() + "</td><td>"
					+ landLord.getAddress() + "</td><td>" + landLord.getStatus() + "</td></tr>");
		}
		printWriter.println("</table><br><br><br></body></html>");
		
		printWriter.write("<form action=\"admin.jsp\" method=\"post\">\r\n"
				+ "    <input type=\"submit\" value=\"Go To Admin Home\" />\r\n" + "</form>");

	}

}
