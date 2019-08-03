package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.order_detail.model.Order_detailService;
import com.order_detail.model.Order_detailVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productreport.model.ProductreportService;
import com.promotion.model.PromotionService;
import com.promotion.model.PromotionVO;
import com.wish.list.model.Wish_listService;
import com.wish.list.model.Wish_listVO;

@WebServlet("/front-end/ShoppingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ShoppingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");

		String action = req.getParameter("action");

		if ("insertIntoWishList".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {

			String product = req.getParameter("product_no");
			System.out.println(product);
			
			Wish_listService wlsrv = new Wish_listService();
			List<Wish_listVO> list = wlsrv.getMemberWish_list(memberVO.getMem_no(), product);
			System.out.println(list);
			System.out.println("有來過1");
			if (list.size() == 0) {
				wlsrv.addWish_list(memberVO.getMem_no(), product);
				System.out.println("有來過2");

			} else {

				String url = "/front-end/product/Shop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
//			} catch (Exception e) {
//				errorMsgs.add("?��法�?��?��?�新增�?��?��??:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Wish_list.jsp");
//				failureView.forward(req, res);
//			}

		}
		if ("deleteFromWishList".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String product = req.getParameter("product_no");

				Wish_listService wlsrv = new Wish_listService();
				wlsrv.deleteWish_list(memberVO.getMem_no(), product);

				String url = "/front-end/product/Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法正常刪除資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Info.jsp");
				failureView.forward(req, res);
			}
		}
		if ("cancelorder".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String order = req.getParameter("order_no");

				Order_detailService ods = new Order_detailService();
				ods.changeOrder_detail(order, "O3");

				String url = "/front-end/product/Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法正常刪除資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Info.jsp");
				failureView.forward(req, res);
			}
		}
		if ("plusOne".equals(action)) {
			String product = req.getParameter("product_no");
			ProductService proSrv = new ProductService();
			ProductVO aProduct = proSrv.getOneProduct(product);

			for (int i = 0; i < buylist.size(); i++) {
				ProductVO myProduct = buylist.get(i);

				if (myProduct.getProduct_no().equals(aProduct.getProduct_no())) {
					myProduct.setProduct_quan(myProduct.getProduct_quan() + aProduct.getProduct_quan());
					buylist.setElementAt(myProduct, i);
				}
			}
			session.setAttribute("shoppingcart", buylist);
		}

		if ("minusOne".equals(action)) {
			String product = req.getParameter("product_no");
			ProductService proSrv = new ProductService();
			ProductVO aProduct = proSrv.getOneProduct(product);

			for (int i = 0; i < buylist.size(); i++) {
				ProductVO myProduct = buylist.get(i);

				if (myProduct.getProduct_no().equals(aProduct.getProduct_no())) {
					myProduct.setProduct_quan(myProduct.getProduct_quan() - aProduct.getProduct_quan());
					buylist.setElementAt(myProduct, i);
				}
			}
			session.setAttribute("shoppingcart", buylist);
		}

		if ("addToCart".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			boolean match = false;

			String product = req.getParameter("product_no");
			String productquan = req.getParameter("product_quan");
			ProductService proSrv = new ProductService();
			ProductVO aProduct = proSrv.getOneProduct(product);

			if (aProduct.getProduct_pn().equals("D1")) {
				String product_no = aProduct.getProduct_no();// 找出商品編號
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.findByProductNo(product_no);
				Double promotion_pr = promotionVO.getPromotion_pr(); // 找商品折扣
				Double total = (aProduct.getProduct_pr()) * promotion_pr; // 找商品折扣後價格
				Integer productTotal = total.intValue();
				aProduct.setProduct_pr(productTotal); // 設定商品VO折扣後價格
			}

			Integer proquan = Integer.parseInt(productquan);
			aProduct.setProduct_quan(proquan);

			if (buylist == null) {
				buylist = new Vector<ProductVO>();
				buylist.add(aProduct);
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO myProduct = buylist.get(i);

					// 假若新增的書籍和購物車的書籍一樣時
					if (myProduct.getProduct_no().equals(aProduct.getProduct_no())) {
						myProduct.setProduct_quan(myProduct.getProduct_quan() + aProduct.getProduct_quan());
						buylist.setElementAt(myProduct, i);
						match = true;
					}
				}

				// 假若新增的書籍和購物車的書籍不一樣時
				if (!match)
					buylist.add(aProduct);
			}

			session.setAttribute("shoppingcart", buylist);
//			String url = "/front-end/product/Shop.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);

			PrintWriter out = res.getWriter();
			out.write(" ");
			out.flush();
			out.close();
		}

		if ("towardCart".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer total = 0;
			Integer finalpoint = 0;
			if (buylist != null) {
				for (ProductVO aProduct : buylist) {
					Integer Quan = aProduct.getProduct_quan();
					Integer PriceForOne = aProduct.getProduct_pr();
					total += (PriceForOne * Quan);
				}
				// 模擬會員登入，取得會員資料
				MemberService ms = new MemberService();
				MemberVO member = ms.getOneMember(memberVO.getMem_no());
				session.setAttribute("memberonline", member);

				Integer pointbefore = member.getMem_amo();
				finalpoint = pointbefore - total;

				String amount = String.valueOf(total);
				String pointafter = String.valueOf(finalpoint);
				req.setAttribute("amount", amount);
				req.setAttribute("pointafter", pointafter);
				
				if(Integer.parseInt(pointafter)<=0) {
					errorMsgs.add("您的餘額不足，請修改商品數量或前往儲值");
				}

			}

			String url = "/front-end/product/CheckOut.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("deleteFromCart".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String product = req.getParameter("product_no");
				System.out.println(product);
				if (buylist != null) {
					for (ProductVO aProduct : buylist) {
						String ProNoInList = aProduct.getProduct_no();

						if (ProNoInList.equals(product)) {
							buylist.remove(aProduct);

						}

					}
				}

//				String url = "/front-end/product/Shop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法正常刪除資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Shop.jsp");
				failureView.forward(req, res);
			}

		}
		if ("deleteFromCheckOut".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String product = req.getParameter("product_no");
				Integer total = 0;
				Integer finalpoint = 0;

				if (buylist.size() != 0) {
					Predicate<ProductVO> pre = ProductVO -> ProductVO.getProduct_no().equals(product);
					buylist.removeIf(pre);

//					for (ProductVO aProduct : buylist) {
//						String ProNoInList = aProduct.getProduct_no();
//
//						if (ProNoInList.equals(product)) {
//							buylist.remove(aProduct);
//						}
				}

				MemberService ms = new MemberService();
				MemberVO member = ms.getOneMember(memberVO.getMem_no());
				session.setAttribute("memberonline", member);
				Integer pointbefore = member.getMem_amo();
				finalpoint = pointbefore - total;

				String pointafter = String.valueOf(finalpoint);
				req.setAttribute("pointafter", pointafter);

				PrintWriter out = res.getWriter();
				out.write(" ");
				out.flush();
				out.close();

			} catch (Exception e) {
				errorMsgs.add("無法正常刪除資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/CheckOut.jsp");
				failureView.forward(req, res);
			}
		}
		if ("createOrderDetail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			if (buylist.size() != 0) {
               
				Order_detailService ods = new Order_detailService();
			
                //這裡取得會員編號(測試用)
				MemberVO member = (MemberVO) session.getAttribute("memberVO");
				String memno = member.getMem_no();
				
                //錯誤驗證
				String ocusname = req.getParameter("order_cusname");
				String ocusnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (ocusname == null || (ocusname.trim()).length() == 0) {
					errorMsgs.add("顧客姓名尚未填寫");
				} else if(!ocusname.trim().matches(ocusnameReg)) {
					errorMsgs.add("填寫的內容包含中英文以外的字符，或長度不符規定");
	            }

				String ocusphone = req.getParameter("order_cusphone");
				String ocusphoneReg = "^[0][9][0-9]{2}[0-9]{6}$";
				if (ocusphone == null || (ocusphone.trim()).length() == 0) {
					errorMsgs.add("手機號碼尚未填寫");
				} else if(!ocusphone.trim().matches(ocusphoneReg)) {
					errorMsgs.add("手機號碼不符規定");
	            }
						
				String adr = req.getParameter("adr");
				if (adr == null || (adr.trim()).length() == 0) {
					errorMsgs.add("郵寄地址尚未填寫，或未選擇飯店地址");
				}
				
				String county = req.getParameter("county");
				String district = req.getParameter("district");
				Integer oamosum = new Integer(req.getParameter("order_amosum"));
								
				String pointafter = req.getParameter("pointafter");
				                
				if(Integer.parseInt(pointafter)<=0) {
					errorMsgs.add("您的餘額不足，請修改商品數量或前往儲值");
				}
				
				String amount = String.valueOf(oamosum);
							
				Order_detailVO orderVO = new Order_detailVO();
				orderVO.setOrder_cusname(ocusname);
				orderVO.setOrder_cusphone(ocusphone);
				orderVO.setOrder_cusadr(adr);
				
				
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("amount", amount);
					req.setAttribute("pointafter", pointafter);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/CheckOut.jsp");
					failureView.forward(req, res);
					return;  
				}
				try {
					StringBuilder sb = new StringBuilder();
					sb.append(county).append(district).append(adr);
					String ocusadr = new String(sb);

					// get merchant_no for OrderDetail
					String merNoInList = null;
					for (ProductVO aProduct : buylist) {
						merNoInList = aProduct.getMerchant_no();
					}

					Order_detailVO order_detailVO = ods.addOrder_detailandList(memno, merNoInList, "O1", oamosum, ocusadr, ocusname, ocusphone,
							buylist);
					
					//扣除點數
					MemberService ms = new MemberService();
				    int point = Integer.parseInt(pointafter);				  			    
				    ms.updateAboutMenAmo(memno, point);
				    member.setMem_amo(point);
				    session.setAttribute("memberVO", member);
                    
					session.removeAttribute("shoppingcart");
					req.setAttribute("orderno", order_detailVO);

					String url = "/front-end/product/CompleteOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				} catch (Exception e) {
					errorMsgs.add("無法正常新增訂單:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/CheckOut.jsp");
					failureView.forward(req, res);
				}
			} else {
				errorMsgs.add("您的購物車目前沒有商品呦");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/CheckOut.jsp");
				failureView.forward(req, res);
			}

		}
		if ("towardOrderlist".equals(action)) {

			String orderno = req.getParameter("order_no");
			
			Order_listService ols = new Order_listService();
			List<Order_listVO> olist = ols.getMyOrder_list(orderno);

			session.setAttribute("order_list", olist);

			Order_detailService ods = new Order_detailService();
			Order_detailVO order_detailVO = ods.getOneOrder_detail(orderno);
			List<Order_detailVO> odlist = new ArrayList();
			odlist.add(order_detailVO);

			session.setAttribute("order_detail", odlist);
		}
		if ("findhotel".equals(action)) {

			String lat = req.getParameter("hotel_lat").trim();
			String lng = req.getParameter("hotel_lng").trim();

			Double dlat = Double.parseDouble(lat);
			Double dlng = Double.parseDouble(lng);

			HotelService hs = new HotelService();
			HotelVO hotelVO = hs.getOneHotel(dlng, dlat);
			String hotelname = hotelVO.getHotel_name();
			String hoteladd = hotelVO.getHotel_add();

			HashMap hm = new HashMap();
			hm.put("hotelname", hotelname);
			hm.put("hoteladd", hoteladd);
			JSONObject obj = new JSONObject(hm);

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();

		}		
		if ("findcityhotel".equals(action)) {
			String city = req.getParameter("city");
					
			HotelService hs = new HotelService();
			
			JSONArray array = new JSONArray();
			List<HotelVO> allhotel  = hs.getAll();
			
			for(HotelVO hotel : allhotel) {
				 JSONObject obj = new JSONObject();
			     if(hotel.getHotel_add().contains(city)) {
			    	 try {
			    	    obj.put("aa", hotel.getHotel_name());
			    	    array.put(obj);
					} catch (JSONException e) {
						e.printStackTrace();
					}
			    	 
			     }
			}
			    
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		if ("findhotellatlng".equals(action)) {
			String hotelname = req.getParameter("hotelname");
			
			HotelService hs = new HotelService();
			HotelVO hotelVO = hs.getOneHotel(hotelname);
			Double lat = hotelVO.getHotel_lat();
			Double lng = hotelVO.getHotel_lng();
			String add = hotelVO.getHotel_add();
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("lat",lat);
				obj.put("lng",lng);
				obj.put("add",add);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
			
			
		}
		if ("reportproduct".equals(action)) {
			String product = req.getParameter("product_no");
			String  reportcontent = req.getParameter("reportcon_no");
			
			ProductreportService ps = new ProductreportService();
			ps.addProductreport(product,reportcontent,"PR1");
			
		}
	}
}