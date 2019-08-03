package com.administrator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.administrator.model.AdministratorService;
import com.administrator.model.AdministratorVO;

//@WebServlet("/AdminLogin.do")
public class AdminLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected boolean allowUser(String administrator_no, String administrator_psw) {
		AdministratorService adminSvc = new AdministratorService();
		AdministratorVO administratorVO = adminSvc.getOneAdministrator(administrator_no);
//		System.out.println("memberVO.getMem_psw()="+memberVO.getMem_psw());
		if (administratorVO == null)
			return false;
		else if (!administratorVO.getAdministrator_psw().equals(administrator_psw))
			return false;
		else
			return true;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String administrator_no = req.getParameter("administrator_no");
		String administrator_psw = req.getParameter("administrator_psw");
		
		if(!allowUser(administrator_no, administrator_psw)) {
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>你的帳號, 密碼無效!<BR>");
			out.println("請按此重新登入<A HREF=" + req.getContextPath() + "/back-end/portal/loginforadmin.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			AdministratorService adminSvc = new AdministratorService();
			AdministratorVO administratorVO = adminSvc.getOneAdministrator(administrator_no);
			HttpSession session = req.getSession();
			session.setAttribute("administrator_no", req.getParameter("administrator_no")); // *工作1: 才在session內做已經登入過的標識
			session.setAttribute("administratorVO", administratorVO); // *工作1: 才在session內做已經登入過的標識
			res.sendRedirect(req.getContextPath() + "/back-end/portal/portal.jsp");
		}
	}
}
