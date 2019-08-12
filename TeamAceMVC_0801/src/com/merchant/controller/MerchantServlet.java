package com.merchant.controller;

import java.io.*;
import java.util.*;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.merchant.model.MerchantService;
import com.merchant.model.MerchantVO;
import com.merchant.model.TestGson;

import redis.clients.jedis.Jedis;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@WebServlet("/MerchantServlet1")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MerchantServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//後台查詢單一廠商
		if ("getOne_For_Merchant".equals(action)) { // 來自merchant_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("merchant_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String merchant_no = null;
				try {
					merchant_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MerchantService merchantSvc = new MerchantService();
    			MerchantVO merchantVO = merchantSvc.getOneMerchant(merchant_no);

				if (merchantVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("merchantVO", merchantVO); // 資料庫取出的merchantVO物件,存入req
				String url = "back-end/merchant/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMerchant.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/merchant/portal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//前台點修改
		if ("getOne_For_Update".equals(action)) { // 來自MerchantUpdate.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【MerchantUpdate.jsp】 或  【】 或 【】		
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String merchant_no = new String(req.getParameter("merchant_no"));
				
				/***************************2.開始查詢資料****************************************/
				MerchantService merchantSvc = new MerchantService();
				MerchantVO merchantVO = merchantSvc.getOneMerchant(merchant_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("merchantVO", merchantVO);         // 資料庫取出的merchantVO物件,存入req
//				String url = "front-end/merchant/update_merchant_input.jsp";
				String url = "front-end/merchant/update_MerchantUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_merchant_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
//						.getRequestDispatcher("front-end/merchant/listAllMerchant.jsp");
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		//前台送出修改
		if ("update".equals(action)) { // 來自update_merchant_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【MerchantUpdate.jsp】 或  【】 或 【】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String merchant_no = new String(req.getParameter("merchant_no"));
				
				String merchant_id = req.getParameter("merchant_id");
				String merchant_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_id == null || merchant_id.trim().length() == 0) {
					errorMsgs.add("廠商帳號: 請勿空白");
				} else if(!merchant_id.trim().matches(merchant_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_pass = req.getParameter("merchant_pass");
				String merchant_passReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pass == null || merchant_pass.trim().length() == 0) {
					errorMsgs.add("廠商帳號密碼: 請勿空白");
				} else if(!merchant_pass.trim().matches(merchant_passReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_name = req.getParameter("merchant_name");
				String merchant_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_name == null || merchant_name.trim().length() == 0) {
					errorMsgs.add("廠商商家名稱: 請勿空白");
				} else if(!merchant_name.trim().matches(merchant_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商商家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_pm = req.getParameter("merchant_pm");
				String merchant_pmReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pm == null || merchant_pm.trim().length() == 0) {
					errorMsgs.add("廠商負責人姓名: 請勿空白");
				} else if(!merchant_pm.trim().matches(merchant_pmReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商負責人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_add = req.getParameter("merchant_add").trim();
				if (merchant_add == null || merchant_add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String merchant_tel = req.getParameter("merchant_tel").trim();
				if (merchant_tel == null || merchant_tel.trim().length() == 0) {
					errorMsgs.add("廠商電話請勿空白");
				}
				
				String merchant_email = req.getParameter("merchant_email").trim();
				if (merchant_email == null || merchant_email.trim().length() == 0) {
					errorMsgs.add("廠商電子郵件請勿空白");
				}
				
				String merchant_status = req.getParameter("merchant_status").trim();
				if (merchant_status == null || merchant_status.trim().length() == 0) {
					errorMsgs.add("廠商狀態請勿空白");
				}
				
				String merchant_ps = req.getParameter("merchant_ps").trim();
				if (merchant_ps == null || merchant_ps.trim().length() == 0) {
					errorMsgs.add("廠商說明請勿空白");
				}
				
				

				byte[] merchant_img = null;

				Collection<Part> parts = req.getParts();
					
					for (Part part : parts) {
						if (part.getContentType()!=null) {
							InputStream in = part.getInputStream();
							merchant_img = new byte[in.available()];
							in.read(merchant_img);
							in.close();
						}
					}
					
				
			    if(merchant_img == null || merchant_img.length == 0) {
			    	 merchant_img = new MerchantService().getOneMerchant(merchant_no).getMerchant_img();
			    }
			    
			    if(merchant_add.equals("台中市大甲區")) {
			    	merchant_add = new MerchantService().getOneMerchant(merchant_no).getMerchant_add();
			    }

				MerchantVO merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(merchant_no);
				merchantVO.setMerchant_id(merchant_id);
				merchantVO.setMerchant_pass(merchant_pass);
				merchantVO.setMerchant_name(merchant_name);
				merchantVO.setMerchant_pm(merchant_pm);
				merchantVO.setMerchant_add(merchant_add);
				merchantVO.setMerchant_tel(merchant_tel);
				merchantVO.setMerchant_email(merchant_email);
				merchantVO.setMerchant_status(merchant_status);
				merchantVO.setMerchant_ps(merchant_ps);
				merchantVO.setMerchant_img(merchant_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchantVO", merchantVO); // 含有輸入格式錯誤的merchantVO物件,也存入req
					RequestDispatcher failureView = req
//							.getRequestDispatcher("front-end/merchant/update_merchant_input.jsp");
							.getRequestDispatcher("front-end/merchant/update_MerchantUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MerchantService merchantSvc = new MerchantService();
				merchantVO = merchantSvc.updateMerchant(merchant_no,  merchant_id, merchant_pass, merchant_name,
						 merchant_pm, merchant_add, merchant_tel, merchant_email, merchant_status, merchant_ps, merchant_img);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("merchantVO", merchantVO); // 資料庫update成功後,正確的的merchantVO物件,存入req
				
//				String url = "front-end/merchant/Index/MerchantUpdate.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMerchant.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
//						.getRequestDispatcher("front-end/merchant/update_merchant_input.jsp");
						.getRequestDispatcher("front-end/merchant/update_MerchantUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//前台新增廠商
        if ("insert".equals(action)) { // 來自addMerchant.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String merchant_id = req.getParameter("merchant_id");
				String merchant_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_id == null || merchant_id.trim().length() == 0) {
					errorMsgs.add("廠商帳號: 請勿空白");
				} else if(!merchant_id.trim().matches(merchant_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String merchant_pass = req.getParameter("merchant_pass");
				String merchant_passReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pass == null || merchant_pass.trim().length() == 0) {
					errorMsgs.add("廠商帳號密碼: 請勿空白");
				} else if(!merchant_pass.trim().matches(merchant_passReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_name = req.getParameter("merchant_name");
				String merchant_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_name == null || merchant_name.trim().length() == 0) {
					errorMsgs.add("廠商商家名稱: 請勿空白");
				} else if(!merchant_name.trim().matches(merchant_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商商家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_pm = req.getParameter("merchant_pm");
				String merchant_pmReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pm == null || merchant_pm.trim().length() == 0) {
					errorMsgs.add("廠商負責人姓名: 請勿空白");
				} else if(!merchant_pm.trim().matches(merchant_pmReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商負責人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_add = req.getParameter("merchant_add").trim();
				if (merchant_add == null || merchant_add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String merchant_tel = req.getParameter("merchant_tel").trim();
				if (merchant_tel == null || merchant_tel.trim().length() == 0) {
					errorMsgs.add("廠商電話請勿空白");
				}
				
				String merchant_email = req.getParameter("merchant_email").trim();
				if (merchant_email == null || merchant_email.trim().length() == 0) {
					errorMsgs.add("廠商電子郵件請勿空白");
				}
				
				String merchant_status = req.getParameter("merchant_status").trim();
				if (merchant_status == null || merchant_status.trim().length() == 0) {
					errorMsgs.add("廠商狀態請勿空白");
				}
				
				String merchant_ps = req.getParameter("merchant_ps").trim();
				if (merchant_ps == null || merchant_ps.trim().length() == 0) {
					errorMsgs.add("廠商說明請勿空白");
				}
				
				byte[] merchant_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						merchant_img = new byte[in.available()];
						in.read(merchant_img);
						in.close();
					}
				}
				
				

				MerchantVO merchantVO = new MerchantVO();
				merchantVO.setMerchant_id(merchant_id);
				merchantVO.setMerchant_pass(merchant_pass);
				merchantVO.setMerchant_name(merchant_name);
				merchantVO.setMerchant_pm(merchant_pm);
				merchantVO.setMerchant_add(merchant_add);
				merchantVO.setMerchant_tel(merchant_tel);
				merchantVO.setMerchant_email(merchant_email);
				merchantVO.setMerchant_status(merchant_status);
				merchantVO.setMerchant_ps(merchant_ps);
				merchantVO.setMerchant_img(merchant_img);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchantVO", merchantVO); // 含有輸入格式錯誤的merchantVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MerchantService merchantSvc = new MerchantService();
				merchantVO = merchantSvc.addMerchant(merchant_id, merchant_pass, merchant_name, merchant_pm, merchant_add, merchant_tel, merchant_email, merchant_status, merchant_ps, merchant_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "front-end/merchant/Index/MerchantLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/merchant/Index/MerchantLogin.jsp");
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
				String merchant_no = new String(req.getParameter("merchant_no"));
				
				/***************************2.開始刪除資料***************************************/
				MerchantService merchantSvc = new MerchantService();
				merchantSvc.deleteMerchant(merchant_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "front-end/merchant/listAllMerchant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/merchant/listAllMerchant.jsp");
				failureView.forward(req, res);
			}
		}
		
		//更改單一廠商狀態(後台)
		if ("getOneMerchantStatus_Update".equals(action)) { // 來自listAllMerchant.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String merchant_no = new String(req.getParameter("merchant_no"));
				
				
				String merchant_status = req.getParameter("merchant_status").trim();
				if (merchant_status == null || merchant_status.trim().length() == 0) {
					errorMsgs.add("廠商狀態請勿空白");
				}
				
				

				MerchantVO merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(merchant_no);

				merchantVO.setMerchant_status(merchant_status);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchantVO", merchantVO); // 含有輸入格式錯誤的merchantVO物件,也存入req
					RequestDispatcher failureView = req
//							.getRequestDispatcher("front-end/merchant/update_merchant_input.jsp");
							.getRequestDispatcher("back-end/merchant/Merchant.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MerchantService merchantSvc = new MerchantService();
				merchantVO = merchantSvc.updateOneMerchantStatus(merchant_no, merchant_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("merchantVO", merchantVO); // 資料庫update成功後,正確的的merchantVO物件,存入req
//				String url = "front-end/merchant/listOneMerchant.jsp";
				String url = "back-end/merchant/Merchant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllMerchantUpdate.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
//						.getRequestDispatcher("front-end/merchant/update_merchant_input.jsp");
						.getRequestDispatcher("back-end/merchant/Merchant.jsp");
				failureView.forward(req, res);
			}
		}
		
		//查詢單一狀態有關廠商(後台)
		if ("get_OneStatus_Merchant".equals(action)) { // 來自backEndMerchant_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("merchant_status");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入狀態");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String merchant_status = null;
				try {
					merchant_status = new String(str);
				} catch (Exception e) {
					errorMsgs.add("狀態格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MerchantService merchantSvc = new MerchantService();
				List<MerchantVO> list = merchantSvc.getOneStatusOfAll(merchant_status);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/merchant/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("List<MerchantVO>", list); // 資料庫取出的List<MerchantVO>物件,存入req
				String url = "back-end/merchant/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneStatusOfMerchant.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/merchant/portal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//快速產生廠商
		if ("get_One_Merchant".equals(action)) {
			
//			Gson gson = new Gson();
			// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
			//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
//			String jsonStr = "";			
			
			try {
				// Retrieve form parameters.
				MerchantVO merchantVO1 = new MerchantVO();
				merchantVO1.setMerchant_id("Kadokawa");
				merchantVO1.setMerchant_pass("Kadokawa");
				merchantVO1.setMerchant_name("台中莊園");
				merchantVO1.setMerchant_pm("藍鳳凰");
				merchantVO1.setMerchant_add("105台中市松山區光復北路11巷44號");
				merchantVO1.setMerchant_tel(" (04)27472433");
				merchantVO1.setMerchant_email("servic@kadokawa.com.tw");
				merchantVO1.setMerchant_status("A1");
				merchantVO1.setMerchant_ps("歐洲古堡莊園");

				
//				// Object to JSON
//				jsonStr = gson.toJson(merchantVO1);
//				System.out.println("Object to JSON: " + jsonStr);
//				// JSON to Object
//				System.out.println("JSON to Object: ");
//				MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);
				
							
				req.setAttribute("NewMerchantVO", merchantVO1); // JSON,存入req
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/merchant/addMerchant2.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
			
		}
		
		
		
		//前台新增廠商
        if ("insert2".equals(action)) { // 來自addMerchant.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String merchant_id = req.getParameter("merchant_id");
				String merchant_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_id == null || merchant_id.trim().length() == 0) {
					errorMsgs.add("廠商帳號: 請勿空白");
				} else if(!merchant_id.trim().matches(merchant_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				String merchant_pass = req.getParameter("merchant_pass");
				String merchant_passReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pass == null || merchant_pass.trim().length() == 0) {
					errorMsgs.add("廠商帳號密碼: 請勿空白");
				} else if(!merchant_pass.trim().matches(merchant_passReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_name = req.getParameter("merchant_name");
				String merchant_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_name == null || merchant_name.trim().length() == 0) {
					errorMsgs.add("廠商商家名稱: 請勿空白");
				} else if(!merchant_name.trim().matches(merchant_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商商家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_pm = req.getParameter("merchant_pm");
				String merchant_pmReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchant_pm == null || merchant_pm.trim().length() == 0) {
					errorMsgs.add("廠商負責人姓名: 請勿空白");
				} else if(!merchant_pm.trim().matches(merchant_pmReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商負責人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String merchant_add = req.getParameter("merchant_add").trim();
				if (merchant_add == null || merchant_add.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String merchant_tel = req.getParameter("merchant_tel").trim();
				if (merchant_tel == null || merchant_tel.trim().length() == 0) {
					errorMsgs.add("廠商電話請勿空白");
				}
				
				String merchant_email = req.getParameter("merchant_email").trim();
				if (merchant_email == null || merchant_email.trim().length() == 0) {
					errorMsgs.add("廠商電子郵件請勿空白");
				}
				
				String merchant_status = req.getParameter("merchant_status").trim();
				if (merchant_status == null || merchant_status.trim().length() == 0) {
					errorMsgs.add("廠商狀態請勿空白");
				}
				
				String merchant_ps = req.getParameter("merchant_ps").trim();
				if (merchant_ps == null || merchant_ps.trim().length() == 0) {
					errorMsgs.add("廠商說明請勿空白");
				}
				
				byte[] merchant_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						merchant_img = new byte[in.available()];
						in.read(merchant_img);
						in.close();
					}
				}
				
				

				MerchantVO merchantVO = new MerchantVO(); //資料庫用
				merchantVO.setMerchant_id(merchant_id);
				merchantVO.setMerchant_pass(merchant_pass);
				merchantVO.setMerchant_name(merchant_name);
				merchantVO.setMerchant_pm(merchant_pm);
				merchantVO.setMerchant_add(merchant_add);
				merchantVO.setMerchant_tel(merchant_tel);
				merchantVO.setMerchant_email(merchant_email);
				merchantVO.setMerchant_status(merchant_status);
				merchantVO.setMerchant_ps(merchant_ps);
				merchantVO.setMerchant_img(merchant_img);
				
				MerchantVO merchantVO1 = new MerchantVO(); //Redis資料庫用
				merchantVO1.setMerchant_id(merchant_id);
				merchantVO1.setMerchant_pass(merchant_pass);
				merchantVO1.setMerchant_name(merchant_name);
				merchantVO1.setMerchant_pm(merchant_pm);
				merchantVO1.setMerchant_add(merchant_add);
				merchantVO1.setMerchant_tel(merchant_tel);
				merchantVO1.setMerchant_email(merchant_email);
				merchantVO1.setMerchant_status(merchant_status);
				merchantVO1.setMerchant_ps(merchant_ps);
				
				
				
				Gson gson = new Gson();
				// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
				//連線
				String jsonStr = "";
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("misj");

				
				String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
				
				Random random = new Random();
				StringBuffer stringBuffer = new StringBuffer();
				
				for(int i=0;i<=10;i++) {
					int index = random.nextInt(str.length());
					char c = str.charAt(index);
					stringBuffer.append(c);  
				}
				
				String string = stringBuffer.toString();
				//暫存密碼
				jedis.set("codekey", string);
				//設定一天過期
				jedis.expire("codekey",86400);
				
				
				 //寄信
				  String to = "misjiii0225@gmail.com";
			      
			      String subject = "密碼通知";
			      
			      String ch_name = merchant_id;
			      String passRandom = string;
			      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
			       
			      TestGson mailService = new TestGson();
			      mailService.sendMail(to, subject, messageText);
			      
			      
			    //將廠商密碼改成暫時密碼
				 merchantVO1.setMerchant_pass(string);
				 // Object to JSON
				 jsonStr = gson.toJson(merchantVO1);
				 System.out.println("Object to JSON: " + jsonStr);
				 // JSON to Object
//				 System.out.println("JSON to Object: ");
//				 MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);

				 StringBuilder sb = new StringBuilder("merchant:").append(merchantVO1.getMerchant_id());
				 jedis.set(sb.toString(), jsonStr);
				 System.out.println(jedis.get(sb.toString()));
				
				 jedis.close();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchantVO", merchantVO); // 含有輸入格式錯誤的merchantVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MerchantService merchantSvc = new MerchantService();
				merchantVO = merchantSvc.addMerchant(merchant_id, merchant_pass, merchant_name, merchant_pm, merchant_add, merchant_tel, merchant_email, merchant_status, merchant_ps, merchant_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "front-end/merchant/login2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/merchant/Index/MerchantLogin.jsp");
				failureView.forward(req, res);
			}finally {
				
			}
				
			
		}
    	
    	
	}
}



