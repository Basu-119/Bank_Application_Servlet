<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="checkpass"  method="post">
		<label>Old password</label> <input type="text" name="old">
		 <label>New
			password</label> <input type="text" name="newpass">
			
	 <label>Conform
			password</label> <input type="text" name="confirm">
			<button type="submit">submit</button>
		</form>
</body>
</html>