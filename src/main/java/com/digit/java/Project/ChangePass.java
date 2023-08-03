package com.digit.java.Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/checkpass")
public class ChangePass extends HttpServlet{

	private HttpSession session;
	private Connection con;
	private PreparedStatement psta;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		int newpasss = Integer.parseInt(req.getParameter("newpass"));
		int confirm = Integer.parseInt(req.getParameter("confirm"));
		int old = Integer.parseInt(req.getParameter("old"));

		
		int id = (int) session.getAttribute("id");
		int pin = (int) session.getAttribute("pin");
		if(newpasss==confirm) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "update bankapp set pin=? where cust_id=? and pin=?";

			psta = con.prepareStatement(sql);
			psta.setInt(1, newpasss);
			psta.setInt(2, id);
			psta.setInt(3, pin);
			

			int x = psta.executeUpdate();
			if (x>0) {

				resp.sendRedirect("/Banking_Application/pinsuccess.jsp");
			} else {
				resp.sendRedirect("/Banking_Application/BalanceFail.jsp");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		
	}
	
}
