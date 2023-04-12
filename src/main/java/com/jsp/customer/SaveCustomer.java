package com.jsp.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Customer;
import com.jsp.service.CustomerService;

@WebServlet("/SaveCustomer")
public class SaveCustomer extends HttpServlet {
	CustomerService customerService = new CustomerService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = new Customer();
		String name = req.getParameter("customer_name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String AadharNo = req.getParameter("aadhar_no");
		String Customerphnno = req.getParameter("customer_phone_no");

		if (!(name.isBlank() || username.isBlank() || password.isBlank() || AadharNo.isBlank()
				|| Customerphnno.isBlank())) {

			customer.setCustomer_name(name);
			customer.setUsername(username);
			customer.setPassword(password);
			customer.setAadhar_no(AadharNo);
			customer.setCustomer_phone_no(Customerphnno);

			if (customerService.validate(customer) && customerService.validatName(customer)) {
				customerService.create(customer);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "Account Created Successfully" + " </h1></body></html>");
				printWriter
						.write("<a href='http://localhost:8080/Hotel_Project/Customerlogin.html'>Go To Login Page</a>");
			} else {
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("<html><body><h1>" + "User Already Exist / Name is Invalid" + " </h1></body></html>");
				printWriter
						.write("<a href='http://localhost:8080/Hotel_Project/Customerlogin.html'>Go To Login Page</a>");

			}

		} else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.write("<html><body><h1>" + "Feild Cannot be empty" + " </h1></body></html>");
			printWriter.write("<a href='http://localhost:8080/Hotel_Project/first.html'>Go To Login Page</a>");
		}

	}
}
