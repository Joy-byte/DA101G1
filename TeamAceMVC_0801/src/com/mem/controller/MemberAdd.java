package com.mem.controller;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.mem.model.*;

//@WebServlet("/MemberAdd.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024
		* 1024)
public class MemberAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		System.out.println("te");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("te3333");
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mem_email = req.getParameter("mem_email");
			String mem_emailReg = "[a-zA-Z0-9._]+@([a-zA-Z0-9_]+.[a-zA-Z0-9_]+)+";
			if (mem_email == null || mem_email.trim().length() == 0) {
				errorMsgs.put("mem_email", "E-mail請勿空白");
			} else if (!mem_email.trim().matches(mem_emailReg)) {
				errorMsgs.put("mem_email", "E-mail格式錯誤");
			}
			
			String mem_psw = req.getParameter("mem_psw");
			if (mem_psw == null || mem_psw.trim().length() == 0) {
				errorMsgs.put("mem_psw", "密碼請勿空白");
			}
			String mem_name = req.getParameter("mem_name");
			if (mem_psw == null || mem_psw.trim().length() == 0) {
				errorMsgs.put("mem_name", "姓名請勿空白");
			}
			String mem_nickname = req.getParameter("mem_nickname");
			if (mem_psw == null || mem_psw.trim().length() == 0) {
				errorMsgs.put("mem_nickname", "暱稱請勿空白");
			}
			java.sql.Date mem_birthday = null;
			try {
				mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
			} catch (IllegalArgumentException e) {
				mem_birthday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("mem_birthday", "請輸入生日!");
			}
			String zone1 = req.getParameter("zone1");
		    String zone2 = req.getParameter("zone2");
			String mem_adr = req.getParameter("mem_adrs");
			String mem_adrs = zone1 + zone2 + mem_adr;
			if (mem_adrs == null || mem_adrs.trim().length() == 0) {
				errorMsgs.put("mem_adrs", "地址請勿空白");
			}
			String mem_tel = req.getParameter("mem_tel");
			String mem_telReg = "[0-9]{10}";
			if (mem_tel == null || mem_tel.trim().length() == 0) {
				errorMsgs.put("mem_tel", "手機號碼請勿空白");
			} else if (!mem_tel.trim().matches(mem_telReg)) {
				errorMsgs.put("mem_tel", "手機號碼格式錯誤");
			}
			String mem_status = req.getParameter("mem_status");

			Part part = req.getPart("mem_pic");
			InputStream in = part.getInputStream();
			byte[] mem_pic = new byte[in.available()];
			in.read(mem_pic);

			MemberVO memberVO = new MemberVO();
			memberVO.setMem_email(mem_email);
			memberVO.setMem_psw(mem_psw);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_nickname(mem_nickname);
			memberVO.setMem_birthday(mem_birthday);
			memberVO.setMem_adrs(mem_adr);
			memberVO.setMem_tel(mem_tel);
			memberVO.setMem_status(mem_status);
			memberVO.setMem_pic(mem_pic);

			if (!errorMsgs.isEmpty()) {
				System.out.println("te44");
				req.setAttribute("memberVO", memberVO);// 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/register.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			System.out.println("te2");
			/*************************** 2.開始新增會員資料 **************************************/
			MemberService memSvc = new MemberService();
			memberVO = memSvc.addMember(mem_email, mem_psw, mem_name, mem_nickname, mem_birthday, mem_adrs, mem_tel,
					mem_status, mem_pic);
			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			out.println("<script>alert(\"感謝您加入悠遊遊，系統將自動發送認證信至您的信箱!\");</script>");
			HttpSession session = req.getSession();
			session.setAttribute("memberVO", memberVO);
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/front-end/home/TeamAce.jsp");
//			String url = "/home/TeamAce2.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//			successView.forward(req, res);
			String to = mem_email;

			String subject = "悠遊遊會員認證通知";

			String ch_name = mem_name;
			String passRandom = "123456";
			String url = "http://localhost:8081/TeamAceMVC/front-end/home/TeamAce.jsp";
			String messageText = "Hello! " + ch_name + "您好:" + "\r\n" + 
					"歡迎您加入悠遊遊會員YoYoYo\r\n" + 
					"請點擊連結，以正式開通您的會員帳號。\r\n" + 
					"\r\n" + 
					"您的會員帳號為: " + to + "\r\n" + 
					"您的會員密碼為: " + mem_psw + "\r\n" + 
					"請點以下連結: " + url + " ，以啟用您的帳號 (此認證信有效時間為4小時)\r\n" + 
					"\r\n" + 
					"認證信件若逾時，請重登入您的帳號，若系統判斷為未開通帳號時，將再次派送新認證信。\r\n" + 
					"若此帳號於註冊日次日未完成開通，將於註冊日後第2天刪除帳號，需重新再次註冊加入。\r\n";

			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);
			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.put("新增資料失敗:", e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/register.jsp");
			failureView.forward(req, res);
		}
	}
	
	public class MailService {
		// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
	
			try {
				// 設定使用SSL連線至 Gmail smtp Server
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
	
				// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
				// ●須將myGmail的【安全性較低的應用程式存取權】打開
				final String myGmail = "ixlogic.wu@gmail.com";
				final String myGmail_password = "BBB45678";
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myGmail, myGmail_password);
					}
				});
	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(myGmail));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	
				// 設定信中的主旨
				message.setSubject(subject);
				// 設定信中的內容
				message.setText(messageText);
	
				Transport.send(message);
				System.out.println("傳送成功!");
			} catch (MessagingException e) {
				System.out.println("傳送失敗!");
				e.printStackTrace();
			}
		}
	}
}
