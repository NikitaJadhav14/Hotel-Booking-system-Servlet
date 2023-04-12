<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Admin</title>
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
	String email = (String) httpSession1.getAttribute("email");
	%>

	<div id="div">
		<p id="title">Hotel Management System</p>
		<h3 align="center">
			<b>Welcome Admin <%=name%></b>
		</h3>

	</div>
	<br>
	<br>
	<form action="UpdateAdmin" method="post">

		<div id="div2">
			<br> <br> <br> <br> <br> <b>Name </b> :<input
				type="text" name="admin_name" value=<%=name%>><br> <br>
			<b>Email </b>:<input type="text" name="admin_email" value=<%=email%>><br>
			<br> <input class="submit" type="submit" name="submit">
		</div>
	</form>


</body>
</html>