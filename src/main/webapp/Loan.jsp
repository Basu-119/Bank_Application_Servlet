<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Selection</title>
<style>
body {
	font-family: Arial, sans-serif;
  background-color: #363958;
	margin: 0;
	padding: 0;
}

form {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
}

h4 {
	margin-bottom: 15px;
	text-align: center;
}

label {
	display: block;
	margin-bottom: 5px;
}

input[type="text"] {
	width: 22rem;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<form action="loan" method="post">
		<h4>
			1. HOME LOAN<br> 2. Education LOAN<br> 3. Vehicle LOAN<br>
			4. Gold LOAN<br> 5. Personal LOAN<br>
		</h4>
		<label>Enter Your Choice</label>
		 <input type="text" name="choice"
			placeholder="Choice"> <input type="submit" value="Submit">
	</form>
</body>
</html>