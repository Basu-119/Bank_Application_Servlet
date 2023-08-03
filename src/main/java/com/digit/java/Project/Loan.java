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

@WebServlet("/loan")
public class Loan extends HttpServlet {
	private Connection con;
	private PreparedStatement psta;
	private ResultSet res;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int choice = Integer.parseInt(req.getParameter("choice"));
		HttpSession session = req.getSession();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "select * from loan where l_id=?";

			psta = con.prepareStatement(sql);
			psta.setInt(1, choice);

			res = psta.executeQuery();
			if (res.next() == true) {
				session.setAttribute("l_id", res.getInt("l_id"));
				session.setAttribute("l_type", res.getString("l_type"));
				session.setAttribute("ten", res.getInt("tenure"));
				session.setAttribute("interest", res.getInt("interest"));
				session.setAttribute("desc", res.getString("desc"));

				resp.sendRedirect("/Banking_Application/Loandet.jsp");
			} else {
				resp.sendRedirect("/Banking_Application/LoanFail.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
