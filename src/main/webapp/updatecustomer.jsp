<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Customer</title>
<link rel="stylesheet" href="./first.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap"
	rel="stylesheet">
</head>
<body>
	<%
	HttpSession httpSession1 = request.getSession();
	%>
	<%
	String name = (String) httpSession1.getAttribute("name");
	%>
	<%
	String username = (String) httpSession1.getAttribute("username");
	%>
	<%
	String aadhar_no = (String) httpSession1.getAttribute("aadhar_no");
	%>
	<%
	String customer_phone_no = (String) httpSession1.getAttribute("customer_phone_no");
	%>
	<div id="div">
		<p id="title">Hotel Management System</p>
		<h3 align="center">
			<b>Welcome <%=name%></b>
		</h3>
	</div>
	<form action="Updatecustomer" method="post">

		<div id="div2">
			<br> <br> <br> <br> <br> <b>Name </b> :<input
				type="text" name="name" value=<%=name%>><br> <br>
			<b>UserName </b>:<input type="text" name="username"
				value=<%=username%>><br>
			<br> <b>Aadhar Num </b>:<input type="text" name="aadharno"
				value=<%=aadhar_no%>><br>
			<br>
			<b>Phone Num </b>:<input type="text" name="phoneNum"
				value=<%=customer_phone_no%>><br> <br> <input
				class="submit" type="submit" name="submit">
		</div>
	</form>

</body>
</html>