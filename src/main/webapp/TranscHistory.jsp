<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
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
session=request.getSession();
ArrayList bank_id= new ArrayList();
int acc_no= (int) session.getAttribute("acc_no");
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
String user = "root";
String pwd = "root";
Connection con = DriverManager.getConnection(url, user, pwd);
String sql = "select * from transfersts where sender_accno=? ";

PreparedStatement psta = con.prepareStatement(sql);
int i;
psta.setInt(1, acc_no);
ResultSet res5 = psta.executeQuery();

%>
<table>
<% 
while(res5.next()==true) {
	%>
	<tr>
	<%
	bank_id.add(res5.getInt(1));
	out.println("<td>"+res5.getString(2)+"</td>");
	out.println("<td>"+res5.getString(3)+"</td>");
	out.println("<td>"+res5.getInt(4)+"</td>");
	out.println("<td>"+res5.getString(5)+"</td>");
	out.println("<td>"+res5.getInt(6)+"</td>");
%>
</tr>
<%
}



%>




</table>



</body>
</html>