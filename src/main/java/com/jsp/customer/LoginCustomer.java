package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

@WebServlet("/Customerlogin")
public class LoginCustomer extends HttpServlet {
	CustomerService customerService = new CustomerService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = new Customer();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (!(username.isBlank() || password.isBlank())) {

			customer.setUsername(username);
			customer.setPassword(password);
			Customer customer2 = customerService.getByUserName(customer);
			if (customer2 != null) {
				if (customerService.logIn(customer2)) {
					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("customerID", customer2.getCustomer_id());
					httpSession.setAttribute("username", customer2.getUsername());
					httpSession.setAttribute("aadhar_no", customer2.getAadhar_no());
					httpSession.setAttribute("name", customer2.getCustomer_name());
					httpSession.setAttribute("customer_phone_no", customer2.getCustomer_phone_no());

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customerlogin.jsp");
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
