<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer</title>
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
	%><div id="div">
		<p id="title">Hotel Management System</p>
		<h3 align="center">
			Welcome to the Page
			<%=name%></h3>
	</div>
	<div id="div2">
		<img id="img1"
			src="https://img.pikbest.com/png-images/qiantu/flat-hotel-logoicon-pattern-icon_2614355.png!bw700">
		<br>
		<form action="getAllHotel" method="post">
			<input class="button2" type="submit" name="name" value="Book Hotel">
		</form>
		<br> <br> <a href="./customer.jsp"> <input
			class="button3" type="button" name="name" value="Edit Details"></a>
		<br> <br>
		<form action="getAllHotel" method="post">
			<input class="button2" type="submit" name="name" value="Book Hotel">
		</form>
		<div id="logout">
			<i align="center" class="fa-solid fa-house"></i> <br> <br>
			<br> <br>
			<form action="logout" method="post">
				<input type="submit" value="Logout">
			</form>
		</div>

	</div>
</body>
</html>