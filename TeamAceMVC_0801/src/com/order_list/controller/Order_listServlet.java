package com.order_list.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.merchant.model.MerchantService;
import com.merchant.model.MerchantVO;
import com.order_list.model.Order_listDAO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.promotion.model.PromotionVO;



@WebServlet("/Order_listServlet1")
public class Order_listServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_UpdateFromOrder_detail".equals(action)) { // 來自OnlyOneOrderDetailOfMerchant.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/order_detail/OnlyOneOrderDetailOfMerchant.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				String order_no = null;
				try {
					order_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/order_detail/OnlyOneOrderDetailOfMerchant.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				Order_listService order_listSvc = new Order_listService();
				List<Order_listVO> list = order_listSvc.findOneOrder_listByOrder_no(order_no);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/order_detail/OnlyOneOrderDetailOfMerchant.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("List<Order_listVO>", list); // 資料庫取出的order_listVO物件,存入req
				String url = "front-end/order_list/listOnlyOneOrder_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOrder_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/order_detail/OnlyOneOrderDetailOfMerchant.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOne_From06".equals(action)) {

			try {
				// Retrieve form parameters.
				String order_no =req.getParameter("order_no");
				
				Order_listService order_listSvc = new Order_listService();
				List<Order_listVO> list = order_listSvc.findOneOrder_listByOrder_no(order_no);
				
				req.setAttribute("List<Order_listVO>", list);
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("back-end/order_list/listOneOrder_listByCompositeQuery.jsp");
//						.getRequestDispatcher("back-end/order_detail/listOrder_detailByCompositeQuery.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		if ("getOne_From05".equals(action)) {

			try {
				// Retrieve form parameters.
				String order_no =req.getParameter("order_no");
				String merchant_no =req.getParameter("merchant_no");
				
				Order_listService order_listSvc = new Order_listService();
				List<Order_listVO> list = order_listSvc.findOneOrder_listByOrder_no(order_no,merchant_no);
				
				req.setAttribute("List<Order_listVO>", list);
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("front-end/order_list/listOnlyOneOrder_list.jsp");
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

