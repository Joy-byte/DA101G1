package com.promotion.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.merchant.model.MerchantService;
import com.merchant.model.MerchantVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.promotion.model.PromotionService;
import com.promotion.model.PromotionVO;

@WebServlet("/PromotionServlet1")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PromotionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//後台單一查詢(後台)
		if ("getOne_For_Promotion".equals(action)) { // 來自promotion_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promotion_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String promotion_no = null;
				try {
					promotion_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.getOnePromotion(promotion_no);
				if (promotionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotionVO", promotionVO); // 資料庫取出的promotionVO物件,存入req
				String url = "/back-end/promotion/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/promotion/portal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		//前台按修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String promotion_no = new String(req.getParameter("promotion_no"));
				
				/***************************2.開始查詢資料****************************************/
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.getOnePromotion(promotion_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("promotionVO", promotionVO);         // 資料庫取出的promotionVO物件,存入req
				String url = "/front-end/promotion/PromotionUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotion_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		//前台送出修改
		if ("update".equals(action)) { // 來自OnlyOnePromotionOfMerchant.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String promotion_no = new String(req.getParameter("promotion_no").trim());
				
				String merchant_no = req.getParameter("merchant_no").trim();
				if (merchant_no == null || merchant_no.trim().length() == 0) {
					errorMsgs.add("廠商編號請勿空白");
				}
				
				String product_no = req.getParameter("product_no").trim();
				if (product_no == null || product_no.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}
				
				String promotion_name = req.getParameter("promotion_name").trim();
				if (promotion_name == null || promotion_name.trim().length() == 0) {
					errorMsgs.add("廣告名稱請勿空白");
				}
				
				java.sql.Date promotion_start = null;
				try {
					promotion_start = java.sql.Date.valueOf(req.getParameter("promotion_start").trim());
				} catch (IllegalArgumentException e) {
					promotion_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date promotion_end = null;
				try {
					promotion_end = java.sql.Date.valueOf(req.getParameter("promotion_end").trim());
				} catch (IllegalArgumentException e) {
					promotion_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double promotion_pr = null;
				try {
					promotion_pr = new Double(req.getParameter("promotion_pr").trim());
				} catch (NumberFormatException e) {
					promotion_pr = 0.0;
					errorMsgs.add("折扣請填數字.");
				}
				
				String promotion_ps = req.getParameter("promotion_ps").trim();
				if (promotion_ps == null || promotion_ps.trim().length() == 0) {
					errorMsgs.add("廣告說明請勿空白");
				}
				
				String promotion_status = req.getParameter("promotion_status").trim();
				if (promotion_status == null || promotion_status.trim().length() == 0) {
					errorMsgs.add("廣告狀態請勿空白");
				}
				
				byte[] promotion_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						promotion_img = new byte[in.available()];
						in.read(promotion_img);
						in.close();
					}
				}
				
				
				if(promotion_img == null || promotion_img.length == 0) {
					promotion_img = new PromotionService().getOnePromotion(promotion_no).getPromotion_img();
			    }
				

				PromotionVO promotionVO = new PromotionVO();
				promotionVO.setPromotion_no(promotion_no);
				promotionVO.setMerchant_no(merchant_no);
				promotionVO.setProduct_no(product_no);
				promotionVO.setPromotion_name(promotion_name);
				promotionVO.setPromotion_start(promotion_start);
				promotionVO.setPromotion_end(promotion_end);
				promotionVO.setPromotion_pr(promotion_pr);
				promotionVO.setPromotion_ps(promotion_ps);
				promotionVO.setPromotion_status(promotion_status);
				promotionVO.setPromotion_img(promotion_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的promotionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/merchant/Index/MerchantPromotion.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionVO = promotionSvc.updatePromotion(promotion_no, merchant_no, product_no, promotion_name, promotion_start,promotion_end, promotion_pr,promotion_ps, promotion_status, promotion_img);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotionVO", promotionVO); // 資料庫update成功後,正確的的empVOpromotionVO物件,存入req
				String url = "/front-end/merchant/Index/MerchantPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotion.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/promotion/PromotionUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//前台頁面送出新增廣告(前台)
        if ("insert".equals(action)) { // 來自addPromotion.jsp(front-end)的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String merchant_no = req.getParameter("merchant_no").trim();
				if (merchant_no == null || merchant_no.trim().length() == 0) {
					errorMsgs.add("廠商編號請勿空白");
				}
				
				String product_no = req.getParameter("product_no").trim();
				if (product_no == null || product_no.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}
				
				String promotion_name = req.getParameter("promotion_name").trim();
				if (promotion_name == null || promotion_name.trim().length() == 0) {
					errorMsgs.add("廣告名稱請勿空白");
				}
				
				java.sql.Date promotion_start = null;
				try {
					promotion_start = java.sql.Date.valueOf(req.getParameter("promotion_start").trim());
				} catch (IllegalArgumentException e) {
					promotion_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date promotion_end = null;
				try {
					promotion_end = java.sql.Date.valueOf(req.getParameter("promotion_end").trim());
				} catch (IllegalArgumentException e) {
					promotion_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double promotion_pr = null;
				try {
					promotion_pr = new Double(req.getParameter("promotion_pr").trim());
				} catch (NumberFormatException e) {
					promotion_pr = 0.0;
					errorMsgs.add("折扣請填數字.");
				}
				
				String promotion_ps = req.getParameter("promotion_ps").trim();
				if (promotion_ps == null || promotion_ps.trim().length() == 0) {
					errorMsgs.add("廣告說明請勿空白");
				}
				
				String promotion_status = req.getParameter("promotion_status").trim();
				if (promotion_status == null || promotion_status.trim().length() == 0) {
					errorMsgs.add("廣告狀態請勿空白");
				}
				
				byte[] promotion_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						promotion_img = new byte[in.available()];
						in.read(promotion_img);
						in.close();
					}
				}


				PromotionVO promotionVO = new PromotionVO();
				promotionVO.setMerchant_no(merchant_no);
				promotionVO.setProduct_no(product_no);
				promotionVO.setPromotion_name(promotion_name);
				promotionVO.setPromotion_start(promotion_start);
				promotionVO.setPromotion_end(promotion_end);
				promotionVO.setPromotion_pr(promotion_pr);
				promotionVO.setPromotion_ps(promotion_ps);
				promotionVO.setPromotion_status(promotion_status);
				promotionVO.setPromotion_img(promotion_img);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的promotionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/merchant/Index/MerchantPromotion.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionVO = promotionSvc.addPromotion(merchant_no, product_no, promotion_name, promotion_start, promotion_end, promotion_pr, promotion_ps, promotion_status, promotion_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/merchant/Index/MerchantPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotion.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/promotion/addPromotion.jsp");
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
				String promotion_no = new String(req.getParameter("promotion_no"));
				
				/***************************2.開始刪除資料***************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionSvc.deletePromotion(promotion_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/promotion/listAllPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/promotion/listAllPromotion.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//從後台更改單一廣告上下架狀態
		if ("getOnePromotionStatus_Update".equals(action)) { // 來自listAllPromotion.jsp的請求(後台)
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【MerchantPromotion.jsp】 或  【listAllPromotion.jsp】
			System.out.println( requestURL);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String promotion_no = new String(req.getParameter("promotion_no").trim());
				
				 
				
				String promotion_status = req.getParameter("promotion_status").trim();
				if (promotion_status == null || promotion_status.trim().length() == 0) {
					errorMsgs.add("廣告狀態請勿空白");
				}
				
				
				
				String product_no = req.getParameter("product_no").trim();
				System.out.println(product_no);
				String product_pn = req.getParameter("product_pn").trim();
				System.out.println(product_pn);
				
//				================
				ProductService productSvc = new ProductService();
				ProductVO productVO=productSvc.getOneProduct(product_no);
				productVO.setProduct_pn(product_pn);//用於修改商品

				PromotionVO promotionVO = new PromotionVO();
				promotionVO.setPromotion_no(promotion_no);
				
				promotionVO.setPromotion_status(promotion_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的promotionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionVO = promotionSvc.updatePromotionStatus(promotion_no, promotion_status);
				
				
				productSvc.updateProduct(productVO);//修改商品表格回去
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				if(requestURL.equals("/front-end/merchant/Index/MerchantPromotion.jsp") || requestURL.equals("/back-end/promotion/portal.jsp")) {
					req.setAttribute("List<PromotionVO>",promotionSvc.getOneStatusOfAll(promotion_status));
				}				

				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotion.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		//查詢單一狀態廣告有關廠商(後台)
		if ("get_OneStatus_Promotion".equals(action)) { // 來自backEndPromotion_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promotion_status");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入狀態");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String promotion_status = null;
				try {
					promotion_status = new String(str);
				} catch (Exception e) {
					errorMsgs.add("狀態格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				List<PromotionVO> list = promotionSvc.getOneStatusOfAll(promotion_status);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/promotion/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("List<PromotionVO>", list); // 資料庫取出的promotionVO物件,存入req
				String url = "/back-end/promotion/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/promotion/portal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		//快速產生廣告
		if ("get_One_Promotion".equals(action)) {
			
//			Gson gson = new Gson();
			// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
			//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
//			String jsonStr = "";			
			
			try {
				// Retrieve form parameters.
				PromotionVO promotionVO1 = new PromotionVO();
				promotionVO1.setMerchant_no("ME00001");
				promotionVO1.setProduct_no("PR00001");
				promotionVO1.setPromotion_name("太陽餅特價");
				promotionVO1.setPromotion_start(java.sql.Date.valueOf("2019-07-01"));
				promotionVO1.setPromotion_end(java.sql.Date.valueOf("2019-8-31"));
				promotionVO1.setPromotion_pr(new Double(0.9));
				promotionVO1.setPromotion_ps("太陽餅是用太陽之手製作");
				promotionVO1.setPromotion_status("B1");

				
//				// Object to JSON
//				jsonStr = gson.toJson(merchantVO1);
//				System.out.println("Object to JSON: " + jsonStr);
//				// JSON to Object
//				System.out.println("JSON to Object: ");
//				MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);
				
							
				req.setAttribute("NewPromotionVO", promotionVO1); // JSON,存入req
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/promotion/addPromotion2.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
			
		}
		
		
		
		if ("getOne_From04".equals(action)) {

			try {
				// Retrieve form parameters.
				String promotion_no =req.getParameter("promotion_no");
				
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.getOnePromotion(promotion_no);
				
				req.setAttribute("PromotionVO", promotionVO);
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("front-end/promotion/promotion.jsp");
//						.getRequestDispatcher("back-end/order_detail/listOrder_detailByCompositeQuery.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		
		




	}
}
