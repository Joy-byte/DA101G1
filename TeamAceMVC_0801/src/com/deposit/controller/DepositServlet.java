package com.deposit.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.deposit.model.DepositService;
import com.deposit.model.DepositVO;

import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/DepositServlet1")
public class DepositServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自deposit_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("deposit_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入儲值編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/deposit/deposit_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String deposit_no = null;
				try {
					deposit_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("儲值編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/deposit/deposit_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DepositService depositSvc = new DepositService();
				DepositVO depositVO = depositSvc.getOneDeposit(deposit_no);
				if (depositVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/deposit/deposit_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depositVO", depositVO); // 資料庫取出的depositVO物件,存入req
				String url = "/back-end/deposit/listOneDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/deposit/deposit_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String deposit_no = new String(req.getParameter("deposit_no"));
				
				/***************************2.開始查詢資料****************************************/
				DepositService depositSvc = new DepositService();
				DepositVO depositVO = depositSvc.getOneDeposit(deposit_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("depositVO", depositVO);         // 資料庫取出的depositVO物件,存入req
				String url = "/back-end/deposit/update_Deposit_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Deposit_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/deposit/listAllDeposit.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Deposit_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String deposit_no = new String(req.getParameter("deposit_no"));
				
				String mem_no = req.getParameter("mem_no").trim();
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
					
				
				java.sql.Date deposit_time = null;
				try {
					deposit_time = java.sql.Date.valueOf(req.getParameter("deposit_time").trim());
				} catch (IllegalArgumentException e) {
					deposit_time =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer deposit_amo = new Integer(req.getParameter("deposit_amo").trim());


				
				
				DepositVO depositVO = new DepositVO();
				depositVO.setDeposit_no(deposit_no);
				depositVO.setMem_no(mem_no);
				depositVO.setDeposit_time(deposit_time);
				depositVO.setDeposit_amo(deposit_amo);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("depositVO", depositVO); // 含有輸入格式錯誤的depositVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/deposit/update_Deposit_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DepositService depositSvc = new DepositService();
				depositVO = depositSvc.updateDeposit(deposit_no, mem_no, deposit_time, deposit_amo);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depositVO", depositVO); // 資料庫update成功後,正確的的depositVO物件,存入req
				String url = "/front-end/deposit/listOneDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDeposit.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/deposit/addDeposit.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addDeposit.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				String mem_no = req.getParameter("mem_no");
				
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
					
				
				java.sql.Date deposit_time = null;
//				try {
//					deposit_time = java.sql.Date.valueOf(req.getParameter("deposit_time").trim());
//				} catch (IllegalArgumentException e) {
//					deposit_time =new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				//抓儲值時間
				Date date = new Date();
			    SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    String str1 = bartDateFormat.format(date);
			    deposit_time = java.sql.Date.valueOf(str1);
				
				
				Integer deposit_amo = new Integer(req.getParameter("deposit_amo").trim());			
				
				
				
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.findMenAmoByMenNo(mem_no);
//				MemberJDBCDAO dao = new MemberJDBCDAO();  //找原本會員點數
//				MemberVO memberVO = dao.findMenAmoByMenNo(mem_no);
				System.out.println(memberVO.getMem_no());
				System.out.println(memberVO.getMem_amo());
				
				Integer mem_amo = memberVO.getMem_amo();

				mem_amo = mem_amo + deposit_amo;        //找新增會員點數
				

				DepositVO depositVO = new DepositVO();
				depositVO.setMem_no(mem_no);
				depositVO.setDeposit_time(deposit_time);
				depositVO.setDeposit_amo(deposit_amo);
				
				MemberVO memberVO1 = new MemberVO();
				memberVO1.setMem_no(mem_no);
				memberVO1.setMem_amo(mem_amo);
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO2 = memberSvc.getOneMember(mem_no);
				memberVO2.setMem_amo(mem_amo);;//存入session


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("depositVO", depositVO); // 含有輸入格式錯誤的depositVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/deposit/addDeposit2.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DepositService depositSvc = new DepositService();
				depositVO = depositSvc.addDeposit(mem_no, deposit_time, deposit_amo);
				
				MemberService memSvc2 = new MemberService();
				memberVO1 = memSvc2.updateAboutMenAmo(mem_no, mem_amo);
				
				
				MemberService memSvc3 = new MemberService();	//傳送會員新點數
				MemberVO memberVO3 = memSvc3.findMenAmoByMenNo(mem_no);
				String new_mem_amo = String.valueOf(memberVO3.getMem_amo()); 


				
//				MemberJDBCDAO dao2 = new MemberJDBCDAO();
//				MemberVO memberVO2 = new MemberVO();
//				memberVO2.setMem_no(mem_no);
//				memberVO2.setMem_amo(mem_amo);
//				dao2.updateAboutMenAmo(memberVO2);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("new_mem_amo", new_mem_amo);
				req.setAttribute("deposit_amo", deposit_amo);
				
				HttpSession session = req.getSession();
				session.setAttribute("memberVO",memberVO2);//存新session
				
				String url = "/front-end/deposit/backDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDeposit.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/deposit/addDeposit2.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String deposit_no = new String(req.getParameter("deposit_no"));
				
				/***************************2.開始刪除資料***************************************/
				DepositService depositSvc = new DepositService();
				depositSvc.deleteDeposit(deposit_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/deposit/listAllDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/deposit/listAllDeposit.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
