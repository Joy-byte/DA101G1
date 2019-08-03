package com.guidereport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.guide.model.*;
import com.guidereport.model.*;

@WebServlet("/GuideReportServlet")
public class GuideReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("insert".equals(action)) {
	
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String mem_no = req.getParameter("mem_no");
			String guideTour_no = req.getParameter("guideTour_no");
			String guideRep_reason = req.getParameter("guideRep_reason");
			String guide_no = req.getParameter("guide_no");
			System.out.println("mem_no="+mem_no);
			System.out.println("guideTour_no="+guideTour_no);

			/*************************** 2.開始新增資料 ***************************************/
			GuideReportService Svc = new GuideReportService();
			Svc.addGuideReport(guideTour_no, guideRep_reason, "GR1",mem_no);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String location = req.getContextPath()+"/front-end/guidetour/GuideTourDetail.jsp?guide_no="+guide_no+"&guideTour_no="+guideTour_no+"&report=report";
			res.sendRedirect(location);
		}
		
		if ("getOne_for_update".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			String guideRep_no = req.getParameter("guideRep_no");
			String type = req.getParameter("type");
			
			/*************************** 2.開始修改資料 ****************************************/
			GuideReportService svc = new GuideReportService();
			GuideReportVO vo = svc.getOneGuideReport(guideRep_no);
			if ("yes".equals(type)) {
				svc.updateGuideReport(guideRep_no, vo.getGuideTour_no(), vo.getGuideRep_reason(),"GR2", vo.getMem_no());
			}
			if ("no".equals(type)) {
				svc.updateGuideReport(guideRep_no, vo.getGuideTour_no(), vo.getGuideRep_reason(),"GR3", vo.getMem_no());
			}
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String location = req.getContextPath()+"/back-end/guidereport/GuideReport.jsp";
			res.sendRedirect(location);
		}

	}

}
