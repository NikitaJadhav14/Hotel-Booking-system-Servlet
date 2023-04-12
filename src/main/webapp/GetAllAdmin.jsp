<%@page import="com.jsp.dto.Admin"%>
<%@page import="com.jsp.admin.GetAllAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin List</title>
</head>
<link rel="stylesheet" href="./first.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap"
	rel="stylesheet">
</head>
<body>
	<div id="div">
		<p id="title">Hotel Management System</p>
	</div>
	<div id="div2">
		<%!GetAllAdmin getAllAdmin = new GetAllAdmin();%>
		<br> <br> <br> <br> <br> <br> 
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
			</tr>

			<%
			for (Admin a : getAllAdmin.admins) {
			%>
			<tr>
				<td><%=a.getAdmin_id()%></td>
				<td><%=a.getAdmin_name()%></td>
				<td><%=a.getAdmin_email()%></td>
			</tr>

			<%
			}
			%>

		</table>
	</div>
</body>
</html>