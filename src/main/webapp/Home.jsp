<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Welcome to The Banking application</h1>
	<%
	session = request.getSession();
	String s1 = (String) session.getAttribute("cust_name");
	%>
	<a href="CheckBalance">1.CHECK BALANCE</a>
	<a href="checkpass.jsp">2.Change Password</a>
	<a href="Loan.jsp">3.Apply Load</a>
	<a href="Transfer.html">4.Transfer</a>
	<a href="TranscHistory.jsp">4.Transfer History</a>
	
	<a href="logout">5.Logout</a>

</body>
</html>