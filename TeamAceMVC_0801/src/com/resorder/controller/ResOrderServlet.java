package com.resorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gtclist.model.GTCListService;
import com.gtclist.model.GTCListVO;
import com.gtlist.model.GTListService;
import com.gtlist.model.GTListVO;
import com.guide.model.*;
import com.guidetour.model.*;
import com.landscape.model.LandscapeService;
import com.mem.model.*;
import com.resorder.model.*;

public class ResOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("dateSearch".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String guide_no = req.getParameter("guide_no");
				String searchDate = req.getParameter("searchDate");
				
				/*************************** 2.開始查詢資料 ****************************************/
				JSONArray array = new JSONArray();
				GuideTourService guideTourSvc = new GuideTourService();
				ResOrderService roSvc = new ResOrderService();
				MemberService memSvc = new MemberService();
				List<ResOrderVO> listAll = roSvc.getAll();
				for (ResOrderVO vo : listAll) {
					if(vo.getGuide_no().equals(guide_no) && vo.getResOrder_date().toString().equals(searchDate)) {
						JSONObject obj = new JSONObject();
						obj.put("guide_no", guide_no);
						obj.put("mem_no", vo.getMem_no());
						obj.put("mem_name", memSvc.getOneMember(vo.getMem_no()).getMem_name());
						obj.put("mem_tel", memSvc.getOneMember(vo.getMem_no()).getMem_tel());
						obj.put("mem_email", memSvc.getOneMember(vo.getMem_no()).getMem_email());
						obj.put("resOrder_time", vo.getResOrder_time());
						obj.put("guideTour_no", vo.getGuideTour_no());
						obj.put("guideTour_name", guideTourSvc.getOneGuideTour(vo.getGuideTour_no()).getGuideTour_name());
						obj.put("resOrder_date", vo.getResOrder_date());
						obj.put("resOrder_peo", vo.getResOrder_peo());
						obj.put("resOrder_joinpeo", vo.getResOrder_joinpeo());
						obj.put("resOrder_msg", vo.getResOrder_msg());
						obj.put("resOrder_cancel", vo.getResOrder_cancel());
						obj.put("resOrder_no", vo.getResOrder_no());
						array.put(obj);
					}
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				PrintWriter out = res.getWriter();
				out.write(array.toString());
				out.flush();
				out.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
//		PrintWriter out = res.getWriter();

		// GuideTourDetail
		if ("reservation".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String guideTour_no = req.getParameter("guideTour_no");
			String guide_no = req.getParameter("guide_no");
			String mem_no = req.getParameter("mem_no");
			System.out.println(req.getParameter("resOrder_date"));
			Date resOrder_date = null;
			try {
				resOrder_date = Date.valueOf(req.getParameter("resOrder_date"));
				
			} catch (IllegalArgumentException e) {
				errorMsgs.put("resOrder_date", "請選擇日期");
			}

			Integer resOrder_peo = null;
			String str = req.getParameter("resOrder_peo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("resOrder_peo", "請選擇人數");
			} else {
				resOrder_peo = new Integer(req.getParameter("resOrder_peo"));
			}

			String resOrder_joinpeo = null;
			if ("ok".equals(req.getParameter("resOrder_joinpeo"))) {
				resOrder_joinpeo = "RJ2"; // RJ1:不可共遊
			} else {
				resOrder_joinpeo = "RJ1";
			}

			String resOrder_msg = req.getParameter("resOrder_msg");

			GuideTourService guideTourSvc = new GuideTourService();
			GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
			GuideService guideSvc = new GuideService();
			GuideVO guideVO = guideSvc.getOneGuide(guideTourVO.getGuide_no());

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guidetour/GuideTourDetail.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 ****************************************/
			ResOrderService resOrderSvc = new ResOrderService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			resOrderSvc.addResOrder(mem_no, guideVO.getGuide_no(), guideTour_no, resOrder_date, resOrder_peo,
					resOrder_msg, "R1", resOrder_joinpeo, new Double(0), null, null, Date.valueOf(sdf.format(date)));

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String location = req.getContextPath()+"/front-end/guidetour/GuideTourDetail.jsp?guide_no="+guide_no+"&guideTour_no="+guideTour_no+"&success=success";
			res.sendRedirect(location);

		}

		if ("update".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String type = req.getParameter("type");
			String from = req.getParameter("from");
			String resOrder_no = req.getParameter("resOrder_no");
			String resOrder_cancel = req.getParameter("resOrder_cancel");
			String resOrder_review = req.getParameter("resOrder_review");
			Double resOrder_rate = new Double(0);
			if("rate".equals(type)) {
				resOrder_rate = Double.parseDouble(req.getParameter("rate"));
			}

			/*************************** 2.開始修改資料 ****************************************/
			ResOrderService resOrderSvc = new ResOrderService();
			GuideTourService guideTourSvc = new GuideTourService();
			ResOrderVO resOrderVO = resOrderSvc.getOneResOrder(resOrder_no);
			String guideTour_no = resOrderVO.getGuideTour_no();
			if ("cancel".equals(type)) {
				resOrderSvc.updateResOrder(resOrder_no, resOrderVO.getMem_no(), resOrderVO.getGuide_no(),
						guideTour_no, resOrderVO.getResOrder_date(), resOrderVO.getResOrder_peo(), resOrderVO.getResOrder_msg(),
						"R3", resOrderVO.getResOrder_joinpeo(), resOrderVO.getResOrder_rate(), resOrder_cancel, null, resOrderVO.getResOrder_time());
			}
			if("complete".equals(type)) {
				resOrderSvc.updateResOrder(resOrder_no, resOrderVO.getMem_no(), resOrderVO.getGuide_no(),
						guideTour_no, resOrderVO.getResOrder_date(), resOrderVO.getResOrder_peo(), resOrderVO.getResOrder_msg(),
						"R2", resOrderVO.getResOrder_joinpeo(), resOrderVO.getResOrder_rate(), resOrder_cancel, null, resOrderVO.getResOrder_time());
			}
			if("rate".equals(type)) {
				resOrderSvc.updateResOrder(resOrder_no, resOrderVO.getMem_no(), resOrderVO.getGuide_no(),
						guideTour_no, resOrderVO.getResOrder_date(), resOrderVO.getResOrder_peo(), resOrderVO.getResOrder_msg(),
						"R2", resOrderVO.getResOrder_joinpeo(), resOrder_rate, resOrderVO.getResOrder_cancel(), resOrder_review, resOrderVO.getResOrder_time());
				
				GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
				Double guideTour_rate = Double.parseDouble(guideTourSvc.getGuideTourRate(resOrderVO.getGuideTour_no()));
				guideTourSvc.updateGuideTour(guideTour_no, guideTourVO.getGuide_no(), guideTourVO.getGuideTour_area(),
						guideTourVO.getGuideTour_detail(), guideTourVO.getGuideTour_name(), guideTourVO.getGuideTour_peo(),
						guideTourVO.getGuideTour_pic(), guideTourVO.getGuideTour_status(), guideTour_rate);
			}
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			if("guide".equals(from)) {
				String location = req.getContextPath()+"/front-end/guide/GuideResOrder.jsp";
				res.sendRedirect(location);
			}
			if("mem".equals(from)||"rate".equals(from)) {
				String location = req.getContextPath()+"/front-end/resorder/MemResOrder.jsp?rate=success";
				res.sendRedirect(location);
			}
			if("cancel".equals(from)) {
				String location = req.getContextPath()+"/front-end/resorder/MemResOrder.jsp";
				res.sendRedirect(location);
			}
		}
	}

}
