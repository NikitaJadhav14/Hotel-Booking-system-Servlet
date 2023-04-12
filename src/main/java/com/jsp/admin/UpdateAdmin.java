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
import com.jsp.service.AdminService;

@WebServlet("/UpdateAdmin")
public class UpdateAdmin extends HttpServlet {
	AdminService adminService = new AdminService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();

		Admin admin = new Admin();
		int id = (Integer) httpSession.getAttribute("ID");

		String name = req.getParameter("admin_name");
		String email = req.getParameter("admin_email");

		if (!(name.isBlank() || email.isBlank())) {
			admin.setAdmin_id(id);
			admin.setAdmin_name(name);
			admin.setAdmin_email(email);

			if (adminService.validatName(admin) && adminService.validate(admin)) {
				adminService.update(admin);
				httpSession.setAttribute("name", admin.getAdmin_name());
				httpSession.setAttribute("email", admin.getAdmin_email());

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1> Updated Successfully </h1></body></html>");
				printWriter.write("<form action=\"admin.jsp\" method=\"post\">\r\n"
						+ "    <input type=\"submit\" value=\"Go To Admin Home\" />\r\n" + "</form>");

			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Give Proper Input" + " </h1></body></html>");
			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Give Proper Input" + " </h1></body></html>");
		}

	}
}
