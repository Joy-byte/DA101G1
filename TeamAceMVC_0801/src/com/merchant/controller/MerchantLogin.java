package com.merchant.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.merchant.model.MerchantService;
import com.merchant.model.MerchantVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/MerchantLogin")
public class MerchantLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
//  protected boolean allowUser(String account, String password) {
//    if ("tomcat".equals(account) && "tomcat".equals(password))
//      return true;
//    else
//      return false;
//  }
	
	protected boolean allowUser(String merchant_id, String merchant_pass) {
		MerchantService merchantSvc = new MerchantService();
		MerchantVO merchantVO = merchantSvc.getOneMerchantId(merchant_id);
//		System.out.println(merchantSvc.getOneMerchantId(merchant_id));
		if(merchantVO ==null ) {
			return false;
		}else if(! merchantVO.getMerchant_pass().equals(merchant_pass)) {
			return false;
		}else {
			return true;
		}
	}
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=Big5");
	    PrintWriter out = res.getWriter();

	    // 【取得使用者 帳號(merchant_id) 密碼(merchant_pass)】
	    String merchant_id = req.getParameter("merchant_id");
	    String merchant_pass = req.getParameter("merchant_pass");

	    // 【檢查該帳號 , 密碼是否有效】
	    if (!allowUser(merchant_id, merchant_pass)) {          //【帳號 , 密碼無效時】
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
	      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/MerchantView/MerchantLogin2.jsp>重新登入</A>");
	      out.println("</BODY></HTML>");
	    }else {//【帳號 , 密碼有效時, 才做以下工作】
	      if(true) {
	    	  req.changeSessionId();
	    }
	      
		MerchantService merchantSvc = new MerchantService();
		MerchantVO merchantVO = merchantSvc.getOneMerchantId(merchant_id);
		
	      HttpSession session = req.getSession();
	      session.setAttribute("merchant_id", merchant_id);//*工作1: 才在session內做已經登入過的標識
	      session.setAttribute("merchant_no", merchantVO.getMerchant_no());
	      session.setAttribute("merchant_name", merchantVO.getMerchant_name());
	      
	       try {                                                        
	         String location = (String) session.getAttribute("location");
	         if (location != null) {
	           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
	           res.sendRedirect(location);            
	           return;
	         }
	       }catch (Exception ignored) { }
	      
	      res.sendRedirect(req.getContextPath()+"/front-end/merchant/Index/MerchantLogin.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
	    }
	  }
	}