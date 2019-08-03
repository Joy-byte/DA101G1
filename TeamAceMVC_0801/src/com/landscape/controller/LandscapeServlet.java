package com.landscape.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.landscape.model.*;


public class LandscapeServlet extends HttpServlet {

	private static final long serialVersionUID = -6514451263643178956L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str= req.getParameter("landscape_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String landscape_no = null;
				try {
					landscape_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("景點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LandscapeService landscapeService = new LandscapeService();
				LandscapeVO landscapeVO = landscapeService.getOneLas(landscape_no);
				if (landscapeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("landscapeVO", landscapeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/landscape/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
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
				String landscape_no = new String(req.getParameter("landscape_no"));
				
				/***************************2.開始查詢資料****************************************/
				LandscapeService landscapeService = new LandscapeService();
				LandscapeVO landscapeVO = landscapeService.getOneLas(landscape_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("landscapeVO", landscapeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/landscape/update_landscape_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landscape/landscape.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String landscape_no = new String(req.getParameter("landscape_no").trim());
				
				String landscape_lng = req.getParameter("landscape_lng").trim();
				if (landscape_lng == null || landscape_lng.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");
				}	
				
				String landscape_lat = req.getParameter("landscape_lat").trim();
				if (landscape_lat == null || landscape_lat.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");
				}
				
				String landscape_name = req.getParameter("landscape_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (landscape_name == null || landscape_name.trim().length() == 0) {
					errorMsgs.add("景點姓名: 請勿空白");
				} else if(!landscape_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("景點姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
				String landscape_status = req.getParameter("landscape_status").trim();
				if (landscape_status == null || landscape_status.trim().length() == 0) {
					errorMsgs.add("景點請勿空白");
				}	

				String landscape_add = req.getParameter("landscape_add").trim();
//				enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (landscape_add == null || landscape_add.trim().length() == 0) {
					errorMsgs.add("景點地址: 請勿空白");
				} 
//				else if(!landscape_add.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("景點地址: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
//	            }
				LandscapeVO landscapeVO = new LandscapeVO();
				landscapeVO.setLandscape_no(landscape_no);
				landscapeVO.setLandscape_lng(Double.parseDouble(landscape_lng));
				landscapeVO.setLandscape_lat(Double.parseDouble(landscape_lat));
				landscapeVO.setLandscape_name(landscape_name);
				landscapeVO.setLandscape_status(landscape_status);
				landscapeVO.setLandscape_add(landscape_add);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("landscapeVO", landscapeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landscape/update_landscape_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LandscapeService landscapeSvc = new LandscapeService();
				landscapeVO = landscapeSvc.updateLas(landscape_no, Double.parseDouble(landscape_lng), Double.parseDouble(landscape_lat), landscape_name, landscape_status,landscape_add);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("landscapeVO", landscapeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/landscape/landscapeOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landscape/landscape.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/				
				String landscape_lng = req.getParameter("landscape_lng").trim();
				if (landscape_lng == null || landscape_lng.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");
				}	
				
				String landscape_lat = req.getParameter("landscape_lat").trim();
				if (landscape_lat == null || landscape_lat.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");
				}
				
				String landscape_name = req.getParameter("landscape_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (landscape_name == null || landscape_name.trim().length() == 0) {
					errorMsgs.add("景點姓名: 請勿空白");
				} else if(!landscape_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("景點姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
				String landscape_status = req.getParameter("landscape_status").trim();
				if (landscape_status == null || landscape_status.trim().length() == 0) {
					errorMsgs.add("景點請勿空白");
				}	

				String landscape_add = req.getParameter("landscape_add").trim();
				enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (landscape_add == null || landscape_add.trim().length() == 0) {
					errorMsgs.add("景點姓名: 請勿空白");
				} else if(!landscape_add.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("景點姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }
				

				LandscapeVO landscapeVO = new LandscapeVO();
				landscapeVO.setLandscape_lng(Double.parseDouble(landscape_lng));
				landscapeVO.setLandscape_lat(Double.parseDouble(landscape_lat));
				landscapeVO.setLandscape_name(landscape_name);
				landscapeVO.setLandscape_status(landscape_status);
				landscapeVO.setLandscape_add(landscape_add);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("landscapeVO", landscapeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/landscape/addLandscape.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LandscapeService landscapeSvc = new LandscapeService();
				landscapeVO = landscapeSvc.addLas(Double.parseDouble(landscape_lng), Double.parseDouble(landscape_lat), landscape_name, landscape_status, landscape_add);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/landscape/landscape.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landscape/addLandscape.jsp");
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
				String landscape_no = new String(req.getParameter("landscape_no"));
				
				/***************************2.開始刪除資料***************************************/
				LandscapeService landscapeSvc = new LandscapeService();
				landscapeSvc.deleteLas(landscape_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/landscape/landscape.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/landscape/landscape.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
