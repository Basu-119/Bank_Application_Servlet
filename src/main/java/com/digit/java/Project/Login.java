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

@WebServlet("/login")
public class Login extends HttpServlet {
	private Connection con;
	private PreparedStatement psta;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pin = Integer.parseInt(req.getParameter("pin"));
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			
			String sql = "select * from bankapp where cust_id=? and pin=?";

			psta = con.prepareStatement(sql);
			psta.setString(1, id);
			psta.setInt(2, pin);

			res = psta.executeQuery();
			if (res.next()==true) {
				session.setAttribute("id", res.getInt("cust_id"));
				session.setAttribute("cust_name", res.getString("cust_name"));
				session.setAttribute("bank_name", res.getString("bank_name"));
				session.setAttribute("ifsc_code", res.getString("ifsc_code"));
				session.setAttribute("acc_no", res.getInt("acc_no"));
				session.setAttribute("pin", res.getInt("pin"));
				session.setAttribute("balance", res.getInt("balance"));
				
				
				resp.sendRedirect("/Banking_Application/Home.jsp");
			} else {
				resp.sendRedirect("/Banking_Application/LoginFail.html");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}