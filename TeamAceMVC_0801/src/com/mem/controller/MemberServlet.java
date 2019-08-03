package com.mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.mem.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("getOne_For_Display".equals(action)) { // 來自select_mem.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("mem_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/select_mem.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				String mem_no = null;
//				try {
//					mem_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/select_mem.jsp");
//					failureView.forward(req, res);
//					return;//
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				MemberService memSvc = new MemberService();
//				MemberVO MemberVO = memSvc.getOneMember(mem_no);
//				if (MemberVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/select_mem.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("MemberVO", MemberVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/front-end/listOneMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(mem_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mem_no = req.getParameter("mem_no");
				String mem_email = req.getParameter("mem_email");
				String mem_status = req.getParameter("mem_status");
				Integer mem_amo = new Integer(req.getParameter("mem_amo"));

				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("電子郵件請勿空白");
				}
				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String mem_nickname = req.getParameter("mem_nickname").trim();
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}
				java.sql.Date mem_birthday = null;
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if (mem_adrs == null || mem_adrs.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_tel = req.getParameter("mem_tel").trim();
				if (mem_tel == null || mem_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				Part part = req.getPart("mem_pic");
				String filename = getFileNameFromPart(part);
				byte[] mem_pic = null;
				boolean hasimg = true;
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_psw(mem_psw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_tel(mem_tel);
				memberVO.setMem_status(mem_status);
				memberVO.setMem_amo(mem_amo);
				if (filename == null || part.getContentType() == null) {
					hasimg = false;
				} else {
					InputStream in = part.getInputStream();
					mem_pic = new byte[in.available()];
					in.read(mem_pic);
					memberVO.setMem_pic(mem_pic);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/update_mem_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemberService memSvc = new MemberService();
				if (hasimg)
					memberVO = memSvc.updateMember(mem_no, mem_email, mem_psw, mem_name, mem_nickname, mem_birthday,
							mem_adrs, mem_tel, mem_status, mem_amo, mem_pic);
				else
					memberVO = memSvc.updateMember2(mem_no, mem_email, mem_psw, mem_name, mem_nickname, mem_birthday,
							mem_adrs, mem_tel, mem_status, mem_amo);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				if(requestURL.equals("/back-end/portal/listAllMember.jsp"))
//					req.setAttribute("memberVO", memberVO);
				
				if(requestURL.equals("/front-end/listOneMember.jsp"))
					req.setAttribute("memberVO", memberVO);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}

//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//				
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}
//				
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
//				}
//				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//		/***************************2.開始新增資料***************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//				
//		/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
//				
//		/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("update_status".equals(action)) { // 來自listAllEmp.jsp

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
			
			try {
		/***************************1.接收請求參數***************************************/
				String mem_no = req.getParameter("mem_no");
				String mem_email = req.getParameter("mem_email");
				String mem_psw = req.getParameter("mem_psw");
				String mem_name = req.getParameter("mem_name");
				String mem_nickname = req.getParameter("mem_nickname");
				java.sql.Date mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday"));
				String mem_adrs = req.getParameter("mem_adrs");
				String mem_tel = req.getParameter("mem_tel");
				String mem_status = req.getParameter("mem_status");
				Integer mem_amo = new Integer(req.getParameter("mem_amo"));
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_psw(mem_psw);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_tel(mem_tel);
				memberVO.setMem_status(mem_status);
				memberVO.setMem_amo(mem_amo);
				
		/***************************2.開始修改狀態***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMember2(mem_no, mem_email, mem_psw, mem_name, mem_nickname, mem_birthday,
						mem_adrs, mem_tel, mem_status, mem_amo);
				
		/***************************3.修改完成,準備轉交(Send the Success view)***********/								
//				if(requestURL.equals("/back-end/portal/listAllMember.jsp"))
//					req.setAttribute("memberVO", memberVO);
				if(requestURL.equals("/back-end/portal/listOneMember.jsp")) {
					req.setAttribute("memberVO", memberVO);
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
		/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/portal/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
