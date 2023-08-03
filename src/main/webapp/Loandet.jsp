<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	session = request.getSession();
	out.println("You select the " + session.getAttribute("l_type"));
	out.println("Tenure for your loan is " + session.getAttribute("ten"));
	out.println("rate of Interest is " + session.getAttribute("interest"));
	out.println("Some Information " + session.getAttribute("desc"));
	%>
	<h6>CLick here to return</h6>
	<a class="btn btn-primary" href="/Banking_Application/Home.jsp"
		role="button">Home</a>

</body>
</html>