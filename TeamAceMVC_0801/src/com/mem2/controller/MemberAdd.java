package com.mem2.controller;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mem2.model.MemberJDBCDAO;
import com.mem2.model.MemberVO;
//@WebServlet("/MemberAdd.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 900 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class MemberAdd extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
	  doGet(req,res);
  }
	
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
		  String Member_NAME = req.getParameter("MemName");
		  String Member_ID = req.getParameter("MemId");
		  String Member_PSW = req.getParameter("MemPsw");
		  MemberJDBCDAO dao = new MemberJDBCDAO();		  
		  MemberVO MemberVO = new MemberVO();
		  System.out.println(Member_NAME);
		  System.out.println(Member_ID);
		  System.out.println(Member_PSW);
		  MemberVO.setMember_NAME(Member_NAME);
		  MemberVO.setMember_ID(Member_ID);
		  MemberVO.setMember_PSW(Member_PSW);
		  
		  
//		  Collection<Part> parts = req.getParts();
//		  for (Part part : parts) {
//			  if(getFileNameFromPart(part)!=null && part.getContentType()!=null){
		  		  Part part = req.getPart("Member_PIC");				  
				  InputStream in = part.getInputStream();
				  byte[] buf = new byte[in.available()];
				  in.read(buf);
				  MemberVO.setMember_PIC(buf);				  
				  in.close();				  			  		
//			  }			  
//		  }
		  dao.insert(MemberVO);
		  
		  		  
//		  RequestDispatcher View = req
//					.getRequestDispatcher("/home/TeamAce2.jsp");
//			View.forward(req, res);
		  res.sendRedirect(req.getContextPath()+"/front-end/home/TeamAce.jsp"); 
		  
		  
   }
		 
  
  	 public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			if (filename.length() == 0) {
				return null;
			}
			return filename;
     }
  	 
}

