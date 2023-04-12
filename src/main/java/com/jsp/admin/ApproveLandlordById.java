package com.jsp.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Admin;
import com.jsp.dto.LandLord;
import com.jsp.service.AdminService;
import com.jsp.service.LandlordService;

@WebServlet("/ApproveLandlordById")
public class ApproveLandlordById extends HttpServlet {
	AdminService adminService = new AdminService();
	LandlordService landlordService = new LandlordService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		try {
			int admin_id = (Integer) httpSession.getAttribute("ID");
			int Landlord_id = Integer.parseInt(req.getParameter("landlord_id"));
			LandLord landLord = landlordService.readById(Landlord_id);
			if (landLord != null) {
				Admin admin = new Admin();
				admin.setAdmin_id(admin_id);

				if (adminService.approveLandlordById(Landlord_id, admin)) {
					PrintWriter printWriter = resp.getWriter();
					printWriter.write("<html><body><h1>" + "Approved Landlord Successfully" + " </h1></body></html>");
					printWriter.write("<form action=\"admin.jsp\" method=\"post\">\r\n"
							+ "    <input type=\"submit\" value=\"Go To Admin Home\" />\r\n" + "</form>");
				} else {
					PrintWriter printWriter = resp.getWriter();
					printWriter.write("<html><body><h1>" + "Already Approved" + " </h1></body></html>");
					printWriter.write("<form action=\"admin.jsp\" method=\"post\">\r\n"
							+ "    <input type=\"submit\" value=\"Go To Admin Home\" />\r\n" + "</form>");
				}

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Invalid ID" + " </h1></body></html>");
				printWriter.write("<form action=\"admin.jsp\" method=\"post\">\r\n"
						+ "    <input type=\"submit\" value=\"Go To Admin Home\" />\r\n" + "</form>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
