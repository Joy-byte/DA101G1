package com.promotion.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/PromotionImageShow")
public class PromotionImageShow extends HttpServlet {
	Connection con;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String promotion_no = req.getParameter("promotion_no").trim();
			
			ResultSet rs = stmt.executeQuery(
//				"SELECT picture FROM emp_photo WHERE PID = '漂亮 ' ");
				"SELECT promotion_img FROM promotion WHERE promotion_no = '"+promotion_no+"'");

			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("image"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("promotion_img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/images.jpeg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/404.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req,res);
	}
 

		public void init() throws ServletException {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
				con =ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void destroy() {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}