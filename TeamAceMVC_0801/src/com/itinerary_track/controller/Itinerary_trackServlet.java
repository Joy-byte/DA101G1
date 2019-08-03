package com.itinerary_track.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.itinerary_track.model.Itinerary_trackService;
import com.itinerary_track.model.Itinerary_trackVO;

public class Itinerary_trackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertItinerary_track".equals(action)) {
			try {
				String itinerary_no = req.getParameter("itinerary_no");
				System.out.println(itinerary_no);
				String mem_no = req.getParameter("mem_no");
				Itinerary_trackService ittSvc = new Itinerary_trackService();
				List<Itinerary_trackVO> list = ittSvc.getMemberItinerary_track(itinerary_no, mem_no);
				for(int i = 0; i < list.size(); i++) {
					Itinerary_trackVO itVO = list.get(i);
					if(!itVO.getMem_no().equals(mem_no)) {
						ittSvc.addItinerary_track(itinerary_no, mem_no);
					}
				}
//				System.out.println(list.size());
				if (list.size() == 0) {
					ittSvc.addItinerary_track(itinerary_no, mem_no);
//					String url = "/front-end/listAllItinerary_detail.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);
//					successView.forward(req, res);
				} else {
//					String url = "/front-end/listAllItinerary_detail.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);
//					successView.forward(req, res);
				}
			
				JSONObject obj = new JSONObject();	
				try {
					obj.put("add", "success");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/listAllItinerary_detail.jsp");
//				failureView.forward(req, res);
			}
		}
		
		System.out.println("測試點");
		if ("deleteFromItinerary_track".equals(action)) {
//			System.out.println("測試點2");
			try {
				String itinerary_no = req.getParameter("itinerary_no");
				String mem_no = req.getParameter("mem_no");
				System.out.println(itinerary_no);
				System.out.println(mem_no);
				Itinerary_trackService ittSvc = new Itinerary_trackService();
				ittSvc.deleteItinerary_track(mem_no, itinerary_no);
//				String url = "/front-end/member/profile.jsp";
//				res.sendRedirect(req.getContextPath() + url + "?mem_no=" + mem_no);
//				System.out.println("測試點3");
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				JSONObject obj = new JSONObject();	
				try {
					obj.put("deleteTrack", "success");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
//				String url = "/front-end/member/profile.jsp";
//				res.sendRedirect(req.getContextPath() + url);
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/profile.jsp");
//				failureView.forward(req, res);
			}
		}
	}
}
