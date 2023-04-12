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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
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

		<a href="./updatecustomer.jsp"> <input class="button1"
			type="button" name="Update" value="Update">
		</a> <br> <br> 
		
		<a href="./DeleteCustomer.html"> <input
			class="button2" type="button" name="Delete" value="Delete">
		</a> <br> <br>

		<div id="logout">
			<i align="center" class="fa-solid fa-house"></i> <br>
			<form action="logout" method="post">
				<input type="submit" value="Logout">
			</form>
		</div>
	</div>


</body>
</html>