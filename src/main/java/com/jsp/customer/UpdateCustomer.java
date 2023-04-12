package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

@WebServlet("/Updatecustomer")
public class UpdateCustomer extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		int id = (Integer) httpSession.getAttribute("customerID");

		CustomerService customerService = new CustomerService();
		Customer customer = new Customer();
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String AadharNo = req.getParameter("aadharno");
		String Customerphnno = req.getParameter("phoneNum");

		if (!(name.isBlank() || username.isBlank() || AadharNo.isBlank() || Customerphnno.isBlank())) {
			customer.setCustomer_id(id);
			customer.setCustomer_name(name);
			customer.setUsername(username);
			customer.setAadhar_no(AadharNo);
			customer.setCustomer_phone_no(Customerphnno);
			if (customerService.validatName(customer)) {
				customerService.update(customer);
				httpSession.setAttribute("customerID", customer.getCustomer_id());
				httpSession.setAttribute("username", customer.getUsername());
				httpSession.setAttribute("aadhar_no", customer.getAadhar_no());
				httpSession.setAttribute("name", customer.getCustomer_name());
				httpSession.setAttribute("customer_phone_no", customer.getCustomer_phone_no());

				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Updated Successfully" + " </h1></body></html>");
				printWriter.write("<form action=\"Customerlogin.jsp\" method=\"post\">\r\n"
						+ "    <input type=\"submit\" value=\"Go To Customer Lobby\" />\r\n" + "</form>");

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
