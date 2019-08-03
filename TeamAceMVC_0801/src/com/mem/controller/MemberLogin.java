package com.mem.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.mem.model.*;
import com.merchant.model.MerchantService;
import com.merchant.model.MerchantVO;

//@WebServlet("/MemberLogin.do")
public class MemberLogin extends HttpServlet {

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

	protected boolean allowUser2(String merchant_id, String merchant_pass) {
		MerchantService merchantSvc = new MerchantService();
		MerchantVO merchantVO = merchantSvc.getOneMerchantId(merchant_id);
//		System.out.println(merchantSvc.getOneMerchantId(merchant_id));
		if (merchantVO == null) {
			return false;
		} else if (!merchantVO.getMerchant_pass().equals(merchant_pass)) {
			return false;
		} else {
			return true;
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("mem_email");
		String password = req.getParameter("mem_psw");

		if (account.indexOf("@") > -1) {
			// �炎�閰脣董��� , 撖Ⅳ�������
			String mem_email = req.getParameter("mem_email");
			String mem_psw = req.getParameter("mem_psw");

			if (!allowUser(mem_email, mem_psw)) { // 【帳號 , 密碼無效時】
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的帳號, 密碼無效!<BR>");
				out.println("請按此重新登入<A HREF=" + req.getContextPath() + "/front-end/member/login.jsp>重新登入</A>");
				out.println("</BODY></HTML>");
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem_email(mem_email);
				HttpSession session = req.getSession();
				session.setAttribute("mem_email", req.getParameter("mem_email")); // *工作1: 才在session內做已經登入過的標識
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
				res.sendRedirect(req.getContextPath() + "/front-end/home/TeamAce.jsp"); // *工作3:
																				// (-->如無來源網頁:則重導至login_success.jsp)
			}

		} else {

			String merchant_id = req.getParameter("mem_email");
			String merchant_pass = req.getParameter("mem_psw");

			// �炎�閰脣董��� , 撖Ⅳ�������
			if (!allowUser2(merchant_id, merchant_pass)) { // �董��� , 撖Ⅳ������
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的帳號, 密碼無效!<BR>");
				out.println("請按此重新登入<A HREF=" + req.getContextPath() + "/front-end/member/login.jsp>重新登入</A>");
				out.println("</BODY></HTML>");
//				out.println("<script>alert(\"你的帳號, 密碼無效!請重新登入\");</script>");
			} else {// �董��� , 撖Ⅳ�����, ���誑銝極雿��
				if (true) {
					req.changeSessionId();
				}

				MerchantService merchantSvc = new MerchantService();
				MerchantVO merchantVO = merchantSvc.getOneMerchantId(merchant_id);

				HttpSession session = req.getSession();
				session.setAttribute("merchant_id", merchant_id);// *撌乩��1: ��session���歇蝬�������
				session.setAttribute("merchant_no", merchantVO.getMerchant_no());
				session.setAttribute("merchant_name", merchantVO.getMerchant_name());
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *撌乩��2: ����靘�雯���
																// (-->憒���雯���:����靘�雯���)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/front-end/merchant/Index/MerchantLogin.jsp"); // *撌乩��3:
																										// (-->憒靘�雯���:����login_success.jsp)
			}
		}
	}
}
