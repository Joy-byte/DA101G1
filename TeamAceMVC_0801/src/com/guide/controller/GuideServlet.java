package com.guide.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.guide.model.*;
import com.mem.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024
		* 1024)
public class GuideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		// GuideList.jsp(後台)
		if ("getOne_for_update".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			String guide_no = req.getParameter("guide_no");
			String type = req.getParameter("type");
			String from = req.getParameter("from");
			
			/*************************** 2.開始修改資料 ****************************************/
			GuideService guideSvc = new GuideService();

			if ("停權".equals(type)) {
				guideSvc.updateGuideStatus(guide_no, "GN2");
			}
			if ("復權".equals(type)) {
				guideSvc.updateGuideStatus(guide_no, "GN1");
			}
			GuideVO guideVO = guideSvc.getOneGuide(guide_no);

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			if("listOne".equals(from)) {
				req.setAttribute("guideVO", guideVO);
				req.setAttribute("navLink", "listOne");//為了跳轉時固定listOne分頁
			}
			
			String url = "/back-end/guide/GuideList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// GuideList.jsp(後台)
		if ("getOne_for_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String guide_no = req.getParameter("guide_no");
				String guide_noReg = "^GU[0-9]{5}$";
				if (!guide_no.matches(guide_noReg)) {
					errorMsgs.add("旅伴編號格式錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/guide/GuideList.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 ****************************************/
				GuideService guideSvc = new GuideService();
				GuideVO guideVO = guideSvc.getOneGuide(guide_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("guideVO", guideVO);
				req.setAttribute("navLink", "listOne");//為了跳轉時固定listOne分頁
				String url = "/back-end/guide/GuideList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要查詢的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/guide/GuideList.jsp");
				failureView.forward(req, res);
			}

		}
			
		System.out.println("測試點1");
		// GuideAdd.jsp
		if ("insert".equals(action)) {
			System.out.println("測試點1.0");
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();
				MemberVO memVO = (MemberVO) session.getAttribute("memberVO");
				String mem_no = memVO.getMem_no();
				String guide_area = req.getParameter("guide_area");
				if (guide_area == null || guide_area.trim().length() == 0) {
					errorMsgs.add("．伴遊區域不得為空");
				}
				System.out.println("測試點1.1");
				String guide_hobby = req.getParameter("guide_hobby");
				String hobbyReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(\\s)]*$";
				if (guide_hobby == null || guide_hobby.trim().length() == 0) {
					errorMsgs.add("．興趣不得為空");
				} else if (!guide_hobby.matches(hobbyReg)) {
					errorMsgs.add("．興趣只能輸入中、英文字母，並請用空白鍵隔開");
				}
				
				String guide_stLang = req.getParameter("guide_stLang");
				String guide_ndLang = req.getParameter("guide_ndLang");

				String guide_intro = req.getParameter("guide_intro");
				if (guide_intro.length() > 500) {
					errorMsgs.add("．自我介紹文字超過上限");
				} else if (guide_intro == null || guide_intro.trim().length() == 0) {
					errorMsgs.add("．自我介紹不得為空");
				}

				byte[] guide_pic = null;
				Part part = req.getPart("guide_pic");
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					guide_pic = new byte[in.available()];
					in.read(guide_pic);
					in.close();
				} else {
					guide_pic = memVO.getMem_pic();
				}

				GuideVO guideVO = new GuideVO();
				guideVO.setMem_no(mem_no);
				guideVO.setGuide_area(guide_area);
				guideVO.setGuide_hobby(guide_hobby);
				guideVO.setGuide_stLang(guide_stLang);
				guideVO.setGuide_ndLang(guide_ndLang);
				guideVO.setGuide_intro(guide_intro);
				guideVO.setGuide_pic(guide_pic);
				System.out.println("測試點2");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("guideVO", guideVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guide/GuideAdd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				GuideService guideSvc = new GuideService();
				guideVO = guideSvc.addGuide(mem_no, guide_intro, guide_pic, null, guide_hobby, "GN1", 0.0, 0.0,
						guide_area, 0.0, guide_stLang, guide_ndLang);
//				session.setAttribute("guideVO", guideVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String location = req.getContextPath()+"/front-end/guide/GuideMainPage.jsp";
				res.sendRedirect(location);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guide/GuideAdd.jsp");
				failureView.forward(req, res);
			}

		}
		// GuideUpdate.jsp
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				HttpSession session = req.getSession();
				MemberVO memVO = (MemberVO) session.getAttribute("memberVO");

				GuideService guideSvc = new GuideService();
				GuideVO guideVO = guideSvc.getOneGuideByMemNo(memVO.getMem_no());

				String guide_no = guideVO.getGuide_no();
				String guide_area = req.getParameter("guide_area");
				String guide_status = guideVO.getGuide_status();
				Double guide_lng = guideVO.getGuide_lng();
				Double guide_lat = guideVO.getGuide_lat();
				Double guide_rate = guideVO.getGuide_rate();
				String guide_stLang = req.getParameter("guide_stLang");
				String guide_ndLang = req.getParameter("guide_ndLang");

				String guide_hobby = req.getParameter("guide_hobby");
				String hobbyReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(\\s)]*$";
				if (guide_hobby == null || guide_hobby.trim().length() == 0) {
					errorMsgs.add("．興趣不得為空");
				} else if (!guide_hobby.matches(hobbyReg)) {
					errorMsgs.add("．興趣只能輸入中、英文字母，並請用空白鍵隔開");
				}

				String guide_intro = req.getParameter("guide_intro");
				if (guide_intro.length() > 500) {
					errorMsgs.add("．自我介紹文字超過上限");
				} else if (guide_intro == null || guide_intro.trim().length() == 0) {
					errorMsgs.add("．自我介紹不得為空");
				}

				Part part = req.getPart("guide_pic");
				byte[] guide_pic = null;
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					guide_pic = new byte[in.available()];
					in.read(guide_pic);
				} else {
					guide_pic = guideSvc.getOneGuide(guide_no).getGuide_pic();
				}

				Part part2 = req.getPart("guide_cover");
				byte[] guide_cover = null;
				if (part2.getSize() != 0) {
					InputStream in2 = part2.getInputStream();
					guide_cover = new byte[in2.available()];
					in2.read(guide_cover);
				} else {
					guide_cover = guideSvc.getOneGuide(guide_no).getGuide_cover();
				}

				guideVO.setGuide_area(guide_area);
				guideVO.setGuide_hobby(guide_hobby);
				guideVO.setGuide_stLang(guide_stLang);
				guideVO.setGuide_ndLang(guide_ndLang);
				guideVO.setGuide_pic(guide_pic);
				guideVO.setGuide_cover(guide_cover);
				guideVO.setGuide_intro(guide_intro);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("guideVO", guideVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guide/GuideUpdate.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/

				guideVO = guideSvc.updateGuide(guide_no, guideVO.getMem_no(), guide_intro, guide_pic, guide_cover,
						guide_hobby, guide_status, guide_lng, guide_lat, guide_area, guide_rate, guide_stLang,
						guide_ndLang);
				session.setAttribute("guideVO", guideVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				String location = req.getContextPath()+"/front-end/guide/GuideMainPage.jsp";
				res.sendRedirect(location);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guide/GuideAdd.jsp");
				failureView.forward(req, res);
			}

		}
	}

}
