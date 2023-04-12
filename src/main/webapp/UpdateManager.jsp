<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Manager</title>
<link rel="stylesheet" href="./first.css">
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
	String address = (String) httpSession1.getAttribute("address");
	%>
	<div id="div">
		<p id="title">Hotel Management System</p>
		<h3 align="center">
			Welcome Manager
			<%=name%></h3>
	</div>
	<div id="div2">
		<form action="UpdateManager" method="post">
			<br> <br> <br> <br> <br> <br> <br>
			<b>Name </b> :<input type="text" name="name" value=<%=name%>><br>
			<br> <b>UserName </b>:<input type="text" name="username"
				value=<%=username%>><br> <br> <b>Address </b>:<input
				type="text" name="address" value=<%=address%>><br> <br>

			<input class="submit" type="submit" name="submit">
		</form>
	</div>


</body>
</html>