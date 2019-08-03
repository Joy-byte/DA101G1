package com.productreport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productreport.model.ProductreportService;
import com.productreport.model.ProductreportVO;



@WebServlet("/ProductreportServlet1")
public class ProductreportServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Productreport".equals(action)) { // 來自productreport_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("productreport_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productreport/productreport_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String productreport_no = null;
				try {
					productreport_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productreport/productreport_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductreportService productreportSvc = new ProductreportService();
				ProductreportVO productreportVO = productreportSvc.getOneProductreport(productreport_no);
				if (productreportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productreport/productreport_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productreportVO", productreportVO); // 資料庫取出的productreportVO物件,存入req
				String url = "/back-end/productreport/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneproductreport.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productreport/productreport_select_page.jsp");
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
				String productreport_no = new String(req.getParameter("productreport_no"));
				
				/***************************2.開始查詢資料****************************************/
				ProductreportService productreportSvc = new ProductreportService();
				ProductreportVO productreportVO = productreportSvc.getOneProductreport(productreport_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productreportVO", productreportVO);         // 資料庫取出的productreportVO物件,存入req
				String url = "/back-end/productreport/update_Productreport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_Productreport_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productreport/listAllProductreport.jsp");
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
				String productreport_no = new String(req.getParameter("productreport_no"));
				
				String product_no = req.getParameter("product_no");
				if (product_no == null || product_no.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				}
					
				String reportcon_no = req.getParameter("reportcon_no");
				if (reportcon_no == null || reportcon_no.trim().length() == 0) {
					errorMsgs.add("檢舉說明: 請勿空白");
				}
				
				String reportcon_status = req.getParameter("reportcon_status");
				if (reportcon_status == null || reportcon_status.trim().length() == 0) {
					errorMsgs.add("檢舉狀態: 請勿空白");
				}



				ProductreportVO productreportVO = new ProductreportVO();
				productreportVO.setProductreport_no(productreport_no);
				productreportVO.setProduct_no(product_no);
				productreportVO.setReportcon_no(reportcon_no);
				productreportVO.setReportcon_status(reportcon_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productreportVO", productreportVO); // 含有輸入格式錯誤的productreportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productreport/update_Productreport_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductreportService productreportSvc = new ProductreportService();
				productreportVO = productreportSvc.updateProductreport(productreport_no, product_no, reportcon_no, reportcon_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productreportVO", productreportVO); // 資料庫update成功後,正確的的productreportVO物件,存入req
				String url = "/back-end/productreport/listOneProductreport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProductreport.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productreport/update_Productreport_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		//前台
        if ("insert".equals(action)) { // 來自addProductreport.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				String product_no = req.getParameter("product_no");
				if (product_no == null || product_no.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				}
					
				String reportcon_no = req.getParameter("reportcon_no");
				if (reportcon_no == null || reportcon_no.trim().length() == 0) {
					errorMsgs.add("檢舉說明: 請勿空白");
				}
				
				String reportcon_status = req.getParameter("reportcon_status");
				if (reportcon_status == null || reportcon_status.trim().length() == 0) {
					errorMsgs.add("檢舉狀態: 請勿空白");
				}


				ProductreportVO productreportVO = new ProductreportVO();
				productreportVO.setProduct_no(product_no);
				productreportVO.setReportcon_no(reportcon_no);
				productreportVO.setReportcon_status(reportcon_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productreportVO", productreportVO); // 含有輸入格式錯誤的productreportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/productreport/addProductreport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductreportService productreportSvc = new ProductreportService();
				productreportVO = productreportSvc.addProductreport(product_no, reportcon_no, reportcon_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/MerchantView/MerchantLogin2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProductreport.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/productreport/addProductreport.jsp");
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
				String productreport_no = new String(req.getParameter("productreport_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductreportService productreportSvc = new ProductreportService();
				productreportSvc.deleteProductreport(productreport_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/productreport/listAllProductreport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productreport/listAllProductreport.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//後臺檢舉更改審核狀態
		if ("getOne_Status_Update".equals(action)) { // 來自update_Deposit_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String productreport_no = new String(req.getParameter("productreport_no"));
				
				String product_no = req.getParameter("product_no");
				if (product_no == null || product_no.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				}
					
				
				String reportcon_status = req.getParameter("reportcon_status");
				if (reportcon_status == null || reportcon_status.trim().length() == 0) {
					errorMsgs.add("檢舉狀態: 請勿空白");
				}



				ProductreportVO productreportVO = new ProductreportVO();
				productreportVO.setProductreport_no(productreport_no);
				productreportVO.setProduct_no(product_no);
				productreportVO.setReportcon_status(reportcon_status);
				
				
				//操作商品表格上下架
				ProductVO productVO = new ProductVO();
				productVO.setProduct_no(product_no);
		

				if(reportcon_status.equals("PR2")) {
					String product_status = "C0";
					productVO.setProduct_status(product_status);
				}else {
					String product_status = "C1";
					productVO.setProduct_status(product_status);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productreportVO", productreportVO); // 含有輸入格式錯誤的productreportVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productreport/listAllProductreport.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductreportService productreportSvc = new ProductreportService();
				productreportVO = productreportSvc.updateProductreportStatus(productreport_no, product_no, reportcon_status);
				
				//操作商品表格上下架
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProductStatus(product_no, productVO.getProduct_status());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productreportVO", productreportVO); // 資料庫update成功後,正確的的productreportVO物件,存入req
				String url = "/back-end/productreport/listAllProductreport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProductreport.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productreport/listAllProductreport.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
