package com.itinerary.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itinerary_detail.model.Itinerary_detailService;
import com.itinerary_detail.model.Itinerary_detailVO;
import com.landscape.model.LandscapeService;
import com.landscape.model.LandscapeVO;
import com.mem.model.MemberVO;
import com.picture.model.PictureService;
import com.picture.model.PictureVO;
import com.question.model.JedisQuestionPool;
import com.question.model.JedisQuestionVO;






//@WebServlet("/front-end/itinerary/ItineraryServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 900 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class ItineraryServlet2 extends HttpServlet {
	private static final long serialVersionUID = -3501163087212512005L;

public ItineraryServlet2(){
	super();
}
	List<LandscapeVO> landscapelist;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//Ajax
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");		
		System.out.println(action);
		
		if ("getSelect".equals(action)) {
			String landscape_city = req.getParameter("landscape_cityo");
			String firsttime =req.getParameter("firsttime");
			System.out.println(landscape_city);
			System.out.println(firsttime);
			JSONArray array = new JSONArray();					
			if(firsttime.equals("Y")) {
				LandscapeService landscapeService = new LandscapeService();	
				landscapelist = landscapeService.getAll();
				System.out.println("first");
			}
			for (LandscapeVO las : landscapelist) {		
				 if( las.getLandscape_add().substring(0,3).equals(landscape_city)){
					 JSONObject obj = new JSONObject();
						try {
							obj.put("landscape_no", las.getLandscape_no());
							obj.put("landscape_name", las.getLandscape_name());
							obj.put("landscape_lat", las.getLandscape_lat());
							obj.put("landscape_lng", las.getLandscape_lng());
						} catch (JSONException e) {
							e.printStackTrace();
						}
						array.put(obj);
				 }
			}
			
					
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		
		if ("itineraryDetail".equals(action)) {
			String itinerary_no =req.getParameter("itinerary_no");
			System.out.println(itinerary_no);
			Itinerary_detailService itinerary_detailService = new Itinerary_detailService();
			PictureService pictureService = new PictureService();		
			List<Itinerary_detailVO> list = itinerary_detailService.getLands(itinerary_no);
//			List<PictureVO> list2 = pictureService.getOnePic(itinerary_no);
			JSONArray array = new JSONArray();
			for (Itinerary_detailVO itinerary_detailVO : list) {
				JSONObject obj = new JSONObject();
				System.out.print(itinerary_detailVO.getLandscape_no() + ",");		
				System.out.print(itinerary_detailVO.getItinerary_content() + ",");
				System.out.print(pictureService.getTheOnePic(itinerary_no, itinerary_detailVO.getLandscape_no()).getPic_no() + ",");
				try {
					obj.put("pic_no", pictureService.getTheOnePic(itinerary_no, itinerary_detailVO.getLandscape_no()).getPic_no());
					obj.put("landscape_no", itinerary_detailVO.getLandscape_no());
					obj.put("itinerary_content", itinerary_detailVO.getItinerary_content());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				array.put(obj);
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}	
		
		
		if ("landscapeAll".equals(action)) {
			String landscapeNo =req.getParameter("landscapeNo");
			System.out.println(landscapeNo);
			PictureService pictureService = new PictureService();		
			LandscapeService landscapeService = new LandscapeService();
			List<PictureVO> list = pictureService.getAll();
			LandscapeVO landscapeVO = null;
			JSONArray array = new JSONArray();
			for (PictureVO pictureVO : list) {			
				try {
					if(pictureVO.getLandscape_no().equals(landscapeNo)) {
						JSONObject obj = new JSONObject();	
						landscapeVO=landscapeService.getOneLas(landscapeNo);
						System.out.print(pictureVO.getLandscape_no() + ",");	
						obj.put("pic_no",pictureVO.getPic_no());
						obj.put("landscape_lat",landscapeVO.getLandscape_lat());
						obj.put("landscape_lng",landscapeVO.getLandscape_lng());
						array.put(obj);
					}					
				} catch (JSONException e) {
					e.printStackTrace();
				}	
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}	
//		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		System.out.println(5);
		HttpSession session = req.getSession();
		MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
		String mem_no = memberVO.getMem_no();
		System.out.println(mem_no);
		String action = req.getParameter("action");
		System.out.println(4);
		
		
        if ("insert".equals(action)) {
        	System.out.println(3);
//        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			
//			java.sql.Date itinerary_start = null;
//			try {
//				System.out.println(1);
//				System.out.println(req.getParameter("itinerary_start"));
//				itinerary_start = java.sql.Date.valueOf(req.getParameter("itinerary_start"));
//				System.out.println(2);
//			} catch (IllegalArgumentException e) {
//				itinerary_start=new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
        	List<byte[]> picbufs = new ArrayList<>();
        	List<byte[]> vdobufs = new ArrayList<>();
        	Collection<Part> parts = req.getParts();
    		for (Part part : parts) {			
    			String filename = part.getSubmittedFileName();   //*********Servlet 3.1 才能用    		    		
    			if (filename!=null && part.getContentType()!=null && filename.length()!=0) {	
//    				System.out.println(filename);
//        			System.out.println(filename.length());
//    				String name = part.getName();				
    				InputStream in = part.getInputStream();
    				byte[] buf = new byte[in.available()];
    				in.read(buf);
    				String ContentType = part.getContentType();
    				if(ContentType.substring(0, 5).equals("image"))
    					picbufs.add(buf);
    				else
    					vdobufs.add(buf);
    				in.close();
    			}
    		}
//			Part part = req.getPart("pic");				  
//    		InputStream in = part.getInputStream();
//    		byte[] buf = new byte[in.available()];
//    		in.read(buf);				  
//    		in.close();
//			ItineraryVO itineraryVO = new ItineraryVO();
//			itineraryVO.setItinerary_pic(buf);
    		
//			itineraryVO.setItinerary_start(itinerary_start);
			
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//							req.setAttribute("itineraryVO", itineraryVO); // 含有輸入格式錯誤的empVO物件,也存入req
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/front-end/itinerary/Upload.jsp");
//							failureView.forward(req, res);
//							return;
//			}				
				ItineraryService itinerarySvc = new ItineraryService();
				JedisQuestionPool jedisQuestionPool = new JedisQuestionPool();
				JedisQuestionVO jedisQuestionVO =null;
				String landscape_no[] = req.getParameterValues("landscape_no");
				String qustionOption[] = req.getParameterValues("qustionOption");
				String qustion= req.getParameter("qustion");
				String checkboxes[]= req.getParameterValues("checkboxes");
				for(int i=0; i<checkboxes.length ;i++)
	            {
	                if(checkboxes[i] != null)
	                {
	                    System.out.print(checkboxes[i]);
	                }          
	            }
				jedisQuestionVO.setQusetion(qustion);
				jedisQuestionVO.setQusetionOption1(qustionOption[0]);
				jedisQuestionVO.setQusetionOption2(qustionOption[1]);
				jedisQuestionVO.setQusetionOption3(qustionOption[2]);
//				jedisQuestionVO.setAnswer(checkboxes);
//				for(int i=0;i<qustionOption.length;i++) {
//					jedisQuestionVO.setQusetionOption1(qusetionOption1);
//				}
//				String landscape_city = req.getParameter("landscape_city").trim();
//				System.out.println(landscape_no.length);
//				System.out.println(landscape_city);
				
//				itinerarySvc.addIti(mem_no,"I1",landscape_no, picbufs,vdobufs);
//				jedisQuestionPool.insert(jedisQuestionVO);
				
//				String url =  "/front-end/home/TeamAce.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);								
				res.sendRedirect(req.getContextPath()+"/front-end/home/TeamAce.jsp");
		}
        
        
//        if ("getOne_From06".equals(action)) {
//
//			try {
				// Retrieve form parameters.
//				Integer empno = new Integer(req.getParameter("empno"));
//
//				EmpDAO dao = new EmpDAO();
//				EmpVO empVO = dao.findByPrimaryKey(empno);
//
//				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				
				//Bootstrap_modal
//				boolean openModal=true;
//				req.setAttribute("openModal",openModal );
//				
//				// 取出的empVO送給listOneEmp.jsp
//				RequestDispatcher successView = req
//						.getRequestDispatcher("/front-end/home/TeamAce.jsp");
//				successView.forward(req, res);
//				return;
//
//				// Handle any unusual exceptions
//			} catch (Exception e) {
//				throw new ServletException(e);
//			}
//		}
        
        
	}
				
	
}
