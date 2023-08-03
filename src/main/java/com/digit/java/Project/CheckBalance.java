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

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private Connection con;
	private PreparedStatement psta;
	private HttpSession session;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession();
		int id = (int) session.getAttribute("id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "select * from bankapp where cust_id=?";

			psta = con.prepareStatement(sql);
			psta.setInt(1, id);

			res = psta.executeQuery();
			if (res.next() == true) {
				session.setAttribute("balance", res.getInt("balance"));

				resp.sendRedirect("/Banking_Application/Balancecheck.jsp");
			} else {
				resp.sendRedirect("/Banking_Application/BalanceFail.jsp");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
