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

@WebServlet("/Deletecustomer")
public class DeleteCustomer extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerService customerService = new CustomerService();

		HttpSession httpSession = req.getSession();
		int id = (Integer) httpSession.getAttribute("customerID");

		Customer customer = new Customer();
		customer.setUsername((String) httpSession.getAttribute("username"));
		customer.setPassword(req.getParameter("password"));

		if (customerService.logIn(customer)) {
			customerService.delete(id);
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
