package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/ProductServlet1")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//後台單一查詢
		if ("getOne_For_Display".equals(action)) { // 來自backEndProductselect_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("product_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/product/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String product_no = null;
				try {
					product_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/product/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("back-end/product/portal.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "back-end/product/portal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("back-end/product/portal.jsp");
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
				String product_no = new String(req.getParameter("product_no"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的empVO物件,存入req
				String url = "front-end/product/ProductUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/merchant/Index/MerchantProduct.jsp");
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
				String product_no = new String(req.getParameter("product_no").trim());
				
				String merchant_no = req.getParameter("merchant_no").trim();
				if (merchant_no == null || merchant_no.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}
				
				
				String product_name = req.getParameter("product_name").trim();
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}
				
				
				String product_status = req.getParameter("product_status").trim();
				if (product_status == null || product_status.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
				Integer product_pr = new Integer(req.getParameter("product_pr").trim());
				
				
				String product_typ = req.getParameter("product_typ").trim();
				if (product_typ == null || product_typ.trim().length() == 0) {
					errorMsgs.add("商品種類請勿空白");
				}
				
				String product_pn = req.getParameter("product_pn").trim();
				if (product_pn == null || product_pn.trim().length() == 0) {
					errorMsgs.add("廣告促銷狀態請勿空白");
				}
				
				
				String product_ps = req.getParameter("product_ps").trim();
				if (product_ps == null || product_ps.trim().length() == 0) {
					errorMsgs.add("廣告促銷狀態請勿空白");
				}
				
				
				Integer product_quan = new Integer(req.getParameter("product_quan").trim());
				
				byte[] product_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						product_img = new byte[in.available()];
						in.read(product_img);
						in.close();
					}
				}

				ProductVO productVO = new ProductVO();
				productVO.setProduct_no(product_no);
				productVO.setMerchant_no(merchant_no);
				productVO.setProduct_name(product_name);
				productVO.setProduct_status(product_status);
				productVO.setProduct_pr(product_pr);
				productVO.setProduct_typ(product_typ);
				productVO.setProduct_pn(product_pn);
				productVO.setProduct_ps(product_ps);
				productVO.setProduct_img(product_img);
				productVO.setProduct_quan(product_quan);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/product/ProductUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(product_no, merchant_no, product_name, product_status, product_pr, product_typ, product_pn, product_ps, product_img, product_quan);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/merchant/Index/MerchantProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/product/ProductUpdate.jsp");
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

				String merchant_no = req.getParameter("merchant_no").trim();
				if (merchant_no == null || merchant_no.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}
				
				
				String product_name = req.getParameter("product_name").trim();
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}
				
				
				String product_status = req.getParameter("product_status").trim();
				if (product_status == null || product_status.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
				Integer product_pr = new Integer(req.getParameter("product_pr").trim());
				
				
				String product_typ = req.getParameter("product_typ").trim();
				if (product_typ == null || product_typ.trim().length() == 0) {
					errorMsgs.add("商品種類請勿空白");
				}
				
				String product_pn = req.getParameter("product_pn").trim();
				if (product_pn == null || product_pn.trim().length() == 0) {
					errorMsgs.add("廣告促銷狀態請勿空白");
				}
				
				
				String product_ps = req.getParameter("product_ps").trim();
				if (product_ps == null || product_ps.trim().length() == 0) {
					errorMsgs.add("廣告促銷狀態請勿空白");
				}
				
				
				Integer product_quan = new Integer(req.getParameter("product_quan").trim());
				
				byte[] product_img = null;
				Collection<Part> parts = req.getParts();
				
				for (Part part : parts) {
					if (part.getContentType()!=null) {
						InputStream in = part.getInputStream();
						product_img = new byte[in.available()];
						in.read(product_img);
						in.close();
					}
				}
				
				

				ProductVO productVO = new ProductVO();
				productVO.setMerchant_no(merchant_no);
				productVO.setProduct_name(product_name);
				productVO.setProduct_status(product_status);
				productVO.setProduct_pr(product_pr);
				productVO.setProduct_typ(product_typ);
				productVO.setProduct_pn(product_pn);
				productVO.setProduct_ps(product_ps);
				productVO.setProduct_img(product_img);
				productVO.setProduct_quan(product_quan);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("front-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(merchant_no, product_name, product_status, product_pr, product_typ, product_pn, product_ps, product_img, product_quan);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "front-end/merchant/Index/MerchantProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("front-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
        //修改商品狀態
        if ("getOneProductStatus_Update".equals(action)) { // 來自OnlyOneProductOfMerchant.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String product_no = new String(req.getParameter("product_no").trim());
				
				
				String product_status = req.getParameter("product_status").trim();
				if (product_status == null || product_status.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
				

				ProductVO productVO = new ProductVO();
				productVO.setProduct_no(product_no);
				
				productVO.setProduct_status(product_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/merchant/Index/MerchantProduct.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProductStatus(product_no, product_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/merchant/Index/MerchantProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/merchant/Index/MerchantProduct.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        //後台商品下架
        if ("getOneProductStatus_Update_Back".equals(action)) { // 來自OnlyOneProductOfMerchant.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String product_no = new String(req.getParameter("product_no").trim());
				
				
				String product_status = req.getParameter("product_status").trim();
				if (product_status == null || product_status.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}
				
				

				ProductVO productVO = new ProductVO();
				productVO.setProduct_no(product_no);
				
				productVO.setProduct_status(product_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/Product.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProductStatus(product_no, product_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				//為了讓修改後傳回有全部物件
				
				if(requestURL.equals("/back-end/product/portal.jsp")) {
					if(req.getParameter("product_status_one")!=null){
						req.setAttribute("get_OneStatus_Product",productSvc.getOneStatusOfAll(product_status));
					}else{
						req.setAttribute("get_TwoStatus_Product",productSvc.getOneStatusOfAll(product_status));
					}	
				}	
				
				
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/Product.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
      //後台查詢單一商品狀態
      if ("get_OneStatus_Product".equals(action)) { // 來自backEndProductselect_page.jsp的請求

      	 List<String> errorMsgs = new LinkedList<String>();
      	 // Store this set in the request scope, in case we need to
      	 // send the ErrorPage view.
      	 req.setAttribute("errorMsgs", errorMsgs);

      	try {
      		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
      		String str = req.getParameter("product_status");
      		if (str == null || (str.trim()).length() == 0) {
      					errorMsgs.add("請輸入狀態編號");
      		}
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      			return;//程式中斷
      		}
      				
      		String product_status = null;
      		try {
      			product_status = new String(str);
      		} catch (Exception e) {
      			errorMsgs.add("狀態編號格式不正確");
      		}
      				
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      			return;//程式中斷
      		}
      				
      		/***************************2.開始查詢資料*****************************************/
      		ProductService productSvc = new ProductService();
      		List<ProductVO> list = productSvc.getOneStatusOfAll(product_status);
      		if (list == null) {
      				errorMsgs.add("查無資料");
      		}
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      				failureView.forward(req, res);
      				return;//程式中斷
      		}
      				
      		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
      		req.setAttribute("get_OneStatus_Product", list); // 資料庫取出的empVO物件,存入req
      		String url = "back-end/product/portal.jsp";
      		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
      		successView.forward(req, res);

      		/***************************其他可能的錯誤處理*************************************/
      		} catch (Exception e) {
      			errorMsgs.add("無法取得資料:" + e.getMessage());
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      		}
      	}
      
      
      
    //後台查詢商品狀態和廣告狀態
      if ("get_TwoStatus_Product".equals(action)) { // 來自backEndProductselect_page.jsp的請求

      	 List<String> errorMsgs = new LinkedList<String>();
      	 // Store this set in the request scope, in case we need to
      	 // send the ErrorPage view.
      	 req.setAttribute("errorMsgs", errorMsgs);

      	try {
      		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
      		String str = req.getParameter("product_status");
      		if (str == null || (str.trim()).length() == 0) {
      					errorMsgs.add("請輸入狀態編號");
      		}
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      			return;//程式中斷
      		}
      				
      		String product_status = null;
      		try {
      			product_status = new String(str);
      		} catch (Exception e) {
      			errorMsgs.add("狀態編號格式不正確");
      		}
      		
      		String str2 = req.getParameter("product_pn");
      		if (str == null || (str.trim()).length() == 0) {
      					errorMsgs.add("請輸入廣告狀態編號");
      		}
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      			return;//程式中斷
      		}
      				
      		String product_pn = null;
      		try {
      			product_pn = new String(str2);
      		} catch (Exception e) {
      			errorMsgs.add("廣告狀態編號格式不正確");
      		}
      		
      		
      				
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      			return;//程式中斷
      		}
      				
      		/***************************2.開始查詢資料*****************************************/
      		ProductService productSvc = new ProductService();
      		List<ProductVO> list = productSvc.getOneStatusOfAll(product_status,product_pn);
      		if (list == null) {
      				errorMsgs.add("查無資料");
      		}
      		// Send the use back to the form, if there were errors
      		if (!errorMsgs.isEmpty()) {
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      				failureView.forward(req, res);
      				return;//程式中斷
      		}
      				
      		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
      		req.setAttribute("get_TwoStatus_Product", list); // 資料庫取出的empVO物件,存入req
      		String url = "back-end/product/portal.jsp";
      		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
      		successView.forward(req, res);

      		/***************************其他可能的錯誤處理*************************************/
      		} catch (Exception e) {
      			errorMsgs.add("無法取得資料:" + e.getMessage());
      			RequestDispatcher failureView = req
      				.getRequestDispatcher("back-end/product/portal.jsp");
      			failureView.forward(req, res);
      		}
      	}
      
      
      //商品檢舉
      if ("report".equals(action)) { // 來自listAllEmp.jsp的請求
    	  
    	  HttpSession session = req.getSession();

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String product_no = new String(req.getParameter("product_no"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);   // 資料庫取出的empVO物件,存入req
				
				session.setAttribute("product_no", product_no);
				
				String url = "front-end/productreport/addProductreport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/merchant/Index/MerchantProduct.jsp");
				failureView.forward(req, res);
			}
		}
      
      
      
    //快速產生商品
    if ("get_One_Product".equals(action)) {
    			
//    	Gson gson = new Gson();
    	// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
    	//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    			
//    	String jsonStr = "";			
    			
    	try {
    		
        	ProductVO productVO1 = new ProductVO();
        	productVO1.setMerchant_no("ME00001");
        	productVO1.setProduct_name("莊園門票");
        	productVO1.setProduct_status("C1");
        	productVO1.setProduct_pr(300);
        	productVO1.setProduct_typ("門票");
        	productVO1.setProduct_pn("D2");
        	productVO1.setProduct_ps("台中莊園入門門票");
    		productVO1.setProduct_quan(1);

    				
//    		// Object to JSON
//    		jsonStr = gson.toJson(merchantVO1);
//    		System.out.println("Object to JSON: " + jsonStr);
//    		// JSON to Object
//    		System.out.println("JSON to Object: ");
//    		MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);
    				
    							
    		req.setAttribute("NewProductVO", productVO1); // JSON,存入req
    				
    		// 取出的empVO送給listOneEmp.jsp
    		RequestDispatcher successView = req
    				.getRequestDispatcher("/front-end/product/addProduct2.jsp");
    		successView.forward(req, res);
    		return;

    		// Handle any unusual exceptions
    	} catch (Exception e) {
    				throw new ServletException(e);
    	}
    			
    }
      
      
      

	}
}
