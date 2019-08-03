package com.mem2.controller;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem2.model.MemberJDBCDAO;
import com.mem2.model.MemberVO;
//@WebServlet("/MemberLogin.do")
public class MemberLogin extends HttpServlet {
     /**
	 * 
	 */
	private static final long serialVersionUID = -817803632673297331L;

public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
	  doGet(req,res);
  }
	
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
	  

	  
	  
		  List<String> errorMsgs2 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
		  
		  String Member_ID = req.getParameter("MemId");
		  String Member_PSW = req.getParameter("MemPsw");		  
		  req.setAttribute("errorMsgs", errorMsgs2);
		try {
			if (Member_ID == null || (Member_ID.trim()).length() == 0) {
				errorMsgs2.add("�п�J�b��");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs2.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
				return;//�{�����_
			}
			
		  /***************************2.�}�l�d�߸��*****************************************/	
		  MemberJDBCDAO dao = new MemberJDBCDAO();		  
		  MemberVO MemberVO = dao.findID(Member_ID);
		  if (MemberVO == null) {
			  errorMsgs2.add("�b�����~");			   
		  }else {
			  if(Member_PSW.equals(MemberVO.getMember_PSW())) {	
				  HttpSession session = req.getSession();
				  session.setAttribute("memberVO", MemberVO);    // ��Ʈw���X������,�s�Jsession
				  res.sendRedirect(req.getContextPath()+"/front-end/home/TeamAce2.jsp");
			  }				  
			  else
				  errorMsgs2.add("�K�X���~");
		  }
		  if (!errorMsgs2.isEmpty()) {
			  	
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
				return;//�{�����_
		  }
		}catch (Exception e) {
				errorMsgs2.add("�t�ο��~:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
		}
   }

}

