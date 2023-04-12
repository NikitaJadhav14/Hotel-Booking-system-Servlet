<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Home</title>
<link rel="stylesheet" href="./first.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
</head>
<body>
	<%
	HttpSession httpSession1 = request.getSession();
	%>
	<%
	String name = (String) httpSession1.getAttribute("name");
	%>
	<div id="div">
		<p id="title">Hotel Management System</p>
		<h3 align="center">
			Welcome to the Page Manager
			<%=name%></h3>
	</div>
	<div id="div2">
		<img id="img1"
			src="https://img.pikbest.com/png-images/qiantu/flat-hotel-logoicon-pattern-icon_2614355.png!bw700">
		<br> <br> <a href="./UpdateManager.jsp"> <input
			class="button1" type="button" name="Update" value="Update">
		</a> <br> <br> <a href="./DeleteManager.html"> <input
			class="button2" type="button" name="Delete" value="Delete">
		</a> <br> <br> <a href="./LoginProperty.html"> <input
			class="button4" type="button" name="ManageHotel" value="ManageHotel">
		</a>
		<div id="logout">
			<i align="center" class="fa-solid fa-house"></i> <br>
			<form action="logout" method="post">
				<input type="submit" value="Logout">
			</form>
		</div>
	</div>
</body>
</html>