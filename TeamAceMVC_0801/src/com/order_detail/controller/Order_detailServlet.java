package com.order_detail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.order_detail.model.Order_detailService;
import com.order_detail.model.Order_detailVO;



@WebServlet("/Order_detailServlet1")
public class Order_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//後台單一搜尋一個訂單
		if ("getOne_For_Order_detail".equals(action)) { // 來自order_detail_select_page.jsp的請求

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
							.getRequestDispatcher("back-end/order_detail/portal.jsp");
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
							.getRequestDispatcher("back-end/order_detail/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				Order_detailVO order_detailVO = order_detailSvc.getOneOrder_detail(order_no);

				if (order_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/order_detail/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_detailVO", order_detailVO); // 資料庫取出的order_detailVO物件,存入req
				String url = "back-end/order_detail/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_detail.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/order_detail/portal.jsp");
				failureView.forward(req, res);
			}
		}
		
		


		
		//從前台更改單一訂單出貨狀態
		if ("getOneOrder_detailStatus_Update".equals(action)) { // 來自OnlyOneOrderDetailOfMerchant.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				
				String order_no = new String(req.getParameter("order_no").trim());
				
				
				String order_status = req.getParameter("order_status").trim();
				if (order_status == null || order_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				
				Order_detailVO order_detailVO = new Order_detailVO();
				order_detailVO.setOrder_no(order_no);

				order_detailVO.setOrder_status(order_status);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detailVO",order_detailVO); // 含有輸入格式錯誤的order_detailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				order_detailVO = order_detailSvc.updateOrder_detailStatus(order_no, order_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("order_detailVO", order_detailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "front-end/merchant/Index/MerchantOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交OnlyOneOrderDetailOfMerchant.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		// 單一廠商查詢全部訂單單一狀態並Order_detailVO傳回list
		if ("get_OneStatus_Order_detail".equals(action)) { // 來自OnlyOneStatusOrderDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("order_status");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入狀態編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String order_status = null;
				try {
					order_status = new String(str);
				} catch (Exception e) {
					errorMsgs.add("狀態格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				String str2 = req.getParameter("merchant_no");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String merchant_no = null;
				try {
					merchant_no = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("廠商編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				List<Order_detailVO> list = order_detailSvc.getOneStatusOrder_detailByMerchantNo(order_status, merchant_no);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				req.setAttribute("List<Order_detailVO>", list); // 資料庫取出的order_detailVO物件,存入req
				session.setAttribute("get_OneStatus_Order_detail", list);
				String url = "front-end/order_detail/OnlyOneStatusOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_detail.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/merchant/Index/MerchantOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		//查詢一個月單一廠商有關的訂單
		if ("getOneMonth_One_Merchant".equals(action)) { // 來自OnlyOneStatusOrderDetail.jsp的請求
			
			HttpSession session = req.getSession();

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("month");
				if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入月份");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
					return;//程式中斷
				}
						
				String month = null;
				try {
					month = new String(str);
				} catch (Exception e) {
					errorMsgs.add("月份格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
				}		
						
				String str2 = req.getParameter("merchant_no");
					if (str2 == null || (str2.trim()).length() == 0) {
						errorMsgs.add("請輸入廠商編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
				    }
						
					String merchant_no = null;
					try {
						merchant_no = new String(str2);
					} catch (Exception e) {
						errorMsgs.add("廠商編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}

						
					/***************************2.開始查詢資料*****************************************/
					
					Order_detailService order_detailSvc = new Order_detailService();
					List<Order_detailVO> list = order_detailSvc.getOneMonthOfOneMerchantNo (month,merchant_no);

					if (list == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
						
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					session.setAttribute("month", str);
					session.setAttribute("merchant_no", str2);
					req.setAttribute("List<Order_detailVO>", list); // 資料庫取出的order_detailVO物件,存入req
					String url = "back-end/order_detail/portal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_detail.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
						failureView.forward(req, res);
					}
				}
		
		
		//前台廠商輸入時間區間找當日訂單
		if ("getOrder_detail_Day".equals(action)) { // 來自OnlyOneStatusOrderDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						
			java.sql.Date order_time_start = null;
			try {
				order_time_start = java.sql.Date.valueOf(req.getParameter("order_time_start").trim());
							
			} catch (IllegalArgumentException e) {
				order_time_start=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
						
			SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			String order_time_start1 = bartDateFormat1.format(order_time_start);		
						
			java.sql.Date order_time_end = null;
						
			try {
				order_time_end =java.sql.Date.valueOf(req.getParameter("order_time_end").trim());
			} catch (IllegalArgumentException e) {
				order_time_end=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
						
			SimpleDateFormat bartDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String order_time_end1 = bartDateFormat2.format(order_time_end);
			
			
			
//			Calendar calendar = Calendar.getInstance();
//
//			if(order_time_start.equals(order_time_end)) { 
//				calendar.setTime(order_time_end);
//				calendar.add(Calendar.DATE,1);//天數加1
//			}
//			
//			SimpleDateFormat bartDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
//			String order_time_end1 = bartDateFormat3.format(calendar.getTime());
			
			
			
			
			
			String str = req.getParameter("merchant_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入廠商編號");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/order_detail/order_detail.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
							
			String merchant_no = null;
			try {
				merchant_no = new String(str);
			} catch (Exception e) {
				errorMsgs.add("廠商編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/order_detail/order_detail.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
						
			/***************************2.開始查詢資料*****************************************/
			Order_detailService order_detailSvc = new Order_detailService();
			List<Order_detailVO> list = order_detailSvc.getAllOneDayOfMerchantNo(order_time_start1,order_time_end1, merchant_no);

			if (list == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/order_detail/order_detail.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
						
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			req.setAttribute("List<Order_detailVO>", list); // 資料庫取出的order_detailVO物件,存入req
			session.setAttribute("getOrder_detail_Day", list);
			String url = "front-end/order_detail/order_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneOrder_detail.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("front-end/order_detail/order_detail.jsp");
			failureView.forward(req, res);
			}
			
			}
		
		
		
		
		//萬用複合查詢(傳入參數型態Map)(回傳 List)
		if ("listorderDetail_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("有進來Servlet");

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				System.out.println(map);
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				}
				
				
//				map.get("merchant_no");
//				System.out.println(map.get("merchant_no"));
//
//				map.remove("merchant_no");
//				System.out.println(map.remove("merchant_no"));
				
				
				/***************************2.開始複合查詢***************************************/
				Order_detailService order_detailSvc = new Order_detailService();
				List<Order_detailVO> list  = order_detailSvc.getAll(map);
				
//				 for (Object key : map.keySet()) {
//			            System.out.println(key + " : " + map.get(key));
//			      }
				
				System.out.println("有進來Map");
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listOrder_detailByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("back-end/order_detail/listOrder_detailByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		//萬用複合查詢(傳入參數型態Map)(回傳 List)+merchant_no
		if ("listorderDetail_ByCompositeQuery1".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("有進來Servlet");
			

			try {

					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					//Map<String, String[]> map = req.getParameterMap();
					HttpSession session = req.getSession();
					
					
					String merchant_no = req.getParameter("merchant_no").trim();
					System.out.println(merchant_no);
					
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					System.out.println(map);
					
					
//					map.remove("merchant_no");
					
//					 for (Object key : map.keySet()) {
//					 System.out.println(key + " : " + map.get(key));
//					 }
					 

					System.out.println(map);
					if (req.getParameter("whichPage") == null){
						HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
						session.setAttribute("map",map1);
						map = map1;
						map.remove("merchant_no");
					}
								
					
					/***************************2.開始複合查詢***************************************/
					Order_detailService order_detailSvc = new Order_detailService();
					List<Order_detailVO> list  = order_detailSvc.getAll(map,merchant_no);
					
					System.out.println("有進來Map");
					
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listOrder_detailByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("back-end/order_detail/listOrder_detailByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					

				
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("back-end/order_detail/order_detail_select_page.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}
	

}
