package com.digit.java.Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	private Connection con;
	private PreparedStatement psta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bank_id = Integer.parseInt(req.getParameter("bank_id"));
		String bank_name = req.getParameter("bank_name");
		String ifsc_code = req.getParameter("ifsc_code");
		int acc_no = Integer.parseInt(req.getParameter("acc_no"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int cust_id = Integer.parseInt(req.getParameter("cust_id"));
		int balance = Integer.parseInt(req.getParameter("balance"));
		String cust_name = req.getParameter("cust_name");
		String email_id = req.getParameter("email_id");
		Long phone = Long.parseLong(req.getParameter("phone"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql = "insert into bankapp values(?,?,?,?,?,?,?,?,?,?)";

			psta = con.prepareStatement(sql);
			psta.setInt(1, bank_id);
			psta.setString(2, bank_name);
			psta.setString(3, ifsc_code);
			psta.setInt(4, acc_no);
			psta.setInt(5, pin);
			psta.setInt(6, cust_id);
			psta.setString(7, cust_name);
			psta.setInt(8, balance);
			psta.setString(9, email_id);
			psta.setLong(10, phone);
			int x = psta.executeUpdate();
			if(x>0) {
				resp.sendRedirect("/Banking_Application/RegisterSuccess.html");
			}
			else {
				resp.sendRedirect("/Banking_Application/RegisterFail.html");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
