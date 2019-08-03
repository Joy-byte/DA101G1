package com.picture.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

//@WebServlet("/front-end/home/PictureReader.do")
public class PictureReader extends HttpServlet {

	private static final long serialVersionUID = 605824264239348163L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			String pic_no= req.getParameter("pic_no").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT pic FROM picture WHERE pic_no = '"+pic_no+"'" );

			if (rs.next()) {
				
//				byte[] image = rs.getBytes("pic");
//				if (image != null) {
//					// 縮圖 in server side
//					image = ImageUtil.shrink(image, 50);
//				}
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				
//				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				
				InputStream in = getServletContext().getResourceAsStream("/NoData/no.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
				
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
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