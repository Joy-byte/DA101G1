package com.mem.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.mem.model.*;

//@WebServlet("/MemberLogin.do")
public class MemberLogin3 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected boolean allowUser(String mem_email, String mem_psw) {
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.getOneMem_email(mem_email);
//		System.out.println("memberVO.getMem_psw()="+memberVO.getMem_psw());
		
		if (memberVO == null)
			return false;
		else if (!memberVO.getMem_psw().equals(mem_psw))
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

		// 【取得使用者 帳號(account) 密碼(password)】
		String mem_email = req.getParameter("mem_email");
		String mem_psw = req.getParameter("mem_psw");

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(mem_email, mem_psw)) { // 【帳號 , 密碼無效時】
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>你的帳號, 密碼無效!<BR>");
			out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/member/login.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMem_email(mem_email);
			HttpSession session = req.getSession();
			session.setAttribute("mem_email", mem_email); // *工作1: 才在session內做已經登入過的標識
			session.setAttribute("memberVO", memberVO); // *工作1: 才在session內做已經登入過的標識
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
//			String url = "/home/TeamAce2.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//			successView.forward(req, res);
	      res.sendRedirect(req.getContextPath()+"/home/TeamAce2.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}

//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("mem_id");
//			String str2 = req.getParameter("mem_psw");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("會員帳號不得為空");
//			}
//			if (str2 == null || (str2.trim()).length() == 0) {
//				errorMsgs.add("會員密碼不得為空");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			String mem_id = null;
////			String mem_psw = null;
//			mem_id = new String(str);
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			MemberService memSvc = new MemberService();
//			MemberVO MemberVO = memSvc.getOneMemberID(mem_id);
//			if (MemberVO == null) {
//				errorMsgs.add("查無資料");
//			}
////			else {
//			if (!str2.equals(MemberVO.getMem_psw())) {
////					HttpSession session = req.getSession();
////					session.setAttribute("mem_name", MemberVO.getMem_name()); // ��Ʈw���X������,�s�Jsession
////					res.sendRedirect(req.getContextPath() + "/home/TeamAce2.jsp");
//				errorMsgs.add("密碼不正確~");
//			}
////			else {
////				if (str2.equals(MemberVO.getMem_psw())) {
////					HttpSession session = req.getSession();
////					session.setAttribute("mem_name", MemberVO.getMem_name()); // ��Ʈw���X������,�s�Jsession
////					res.sendRedirect(req.getContextPath() + "/home/TeamAce2.jsp");
////				} else
////					errorMsgs.add("密碼不正確~");
////			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("MemberVO", MemberVO); // ��Ʈw���X��empVO����,�s�Jreq
//			String url = "/member/listOneMember.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//			successView.forward(req, res);
//
//			/*************************** 其他可能的錯誤處理 *************************************/
//		} catch (Exception e) {
//			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
//			failureView.forward(req, res);
//		}
	}
}
