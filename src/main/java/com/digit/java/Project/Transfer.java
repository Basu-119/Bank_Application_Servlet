package com.digit.java.Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	private Connection con;
	private PreparedStatement psta;
	private ResultSet res;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;
	public String msg;
	private int x;
	private int x2;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		int acc_no = (int) session.getAttribute("acc_no");
		int pin = (int) session.getAttribute("pin");
		String bank_name = (String) session.getAttribute("bank_name");
		String ifsc_code = (String) session.getAttribute("ifsc_code");

		String rifsc = req.getParameter("rifsc");
		int racc_no = Integer.parseInt(req.getParameter("racc_no"));
		int amount = Integer.parseInt(req.getParameter("amount"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Bankapp_Database";
			String user = "root";
			String pwd = "root";
			con = DriverManager.getConnection(url, user, pwd);

			String sql1 = "select * from bankapp where acc_no=? and ifsc_code=?";

			psta = con.prepareStatement(sql1);
			psta.setInt(1, racc_no);
			psta.setString(2, rifsc);
			res2 = psta.executeQuery();

			if (res2.next() == true) {
				String sql2 = "select balance from bankapp where acc_no=? ";
				psta = con.prepareStatement(sql2);
				psta.setInt(1, acc_no);
				res3 = psta.executeQuery();
				res3.next();
				int bal = res3.getInt(1);
				if (bal > amount) {
					String sql3 = "update bankapp set balance=balance-? where acc_no=? ";
					psta = con.prepareStatement(sql3);
					psta.setInt(1, amount);
					psta.setInt(2, acc_no);
					x = psta.executeUpdate();

					if (x > 0) {
						String sql4 = "update bankapp set balance=balance+? where acc_no=? ";
						psta = con.prepareStatement(sql4);
						psta.setInt(1, amount);
						psta.setInt(2, racc_no);
						x2 = psta.executeUpdate();
						if (x2 > 0) {
							String sql5 = "insert into transfersts values(?,?,?,?,?,?,?,?) ";
							psta = con.prepareStatement(sql5);
							psta.setInt(1, id);
							psta.setString(2, bank_name);
							psta.setString(3, ifsc_code);
							psta.setInt(4, acc_no);
							psta.setString(5, rifsc);
							psta.setInt(6, racc_no);
							psta.setInt(7, amount);

							Random r = new Random(System.currentTimeMillis());
							int t_id = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));

							psta.setInt(8, t_id);

							int x4 = psta.executeUpdate();
							if (x4 > 0) {
								resp.sendRedirect("/Banking_Application/TransferSuccess.jsp");
							} else {
								msg = "NOT INSERT IN TRANFER TABLE";
								session.setAttribute("msg", msg);
								resp.sendRedirect("/Bank_Application/Transferfail.jsp");
							}

						} else {
							msg = "NOT Credit Into Account";
							session.setAttribute("msg", msg);
							resp.sendRedirect("/Bank_Application/Transferfail.jsp");
						}

					} else {
						msg = "NOT DEBIT TO YOUR ACCOUNT";
						session.setAttribute("msg", msg);
						resp.sendRedirect("/Bank_Application/Transferfail.jsp");
					}
				} else {
					msg = "DONT HAVE SUFFICIENT BALANCE";
					session.setAttribute("msg", msg);
					resp.sendRedirect("/Bank_Application/Transferfail.jsp");
				}

			} else {
				msg = "RECIEVER ID DOEN NOT MATCH";
				session.setAttribute("msg", msg);
				resp.sendRedirect("/Bank_Application/Transferfail.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
