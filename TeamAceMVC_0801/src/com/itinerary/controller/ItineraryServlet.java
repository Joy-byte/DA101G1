package com.itinerary.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itinerary.model.ItineraryService;
import com.itinerary.model.ItineraryVO;
import com.itineraryStart.model.ItineraryStartService;
import com.itinerary_detail.model.Itinerary_detailService;
import com.itinerary_detail.model.Itinerary_detailVO;
import com.landscape.model.LandscapeService;
import com.landscape.model.LandscapeVO;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.picture.model.PictureService;
import com.picture.model.PictureVO;
import com.question.model.JedisQuestionPool;
import com.question.model.JedisQuestionVO;
import com.video.model.VideoService;
import com.video.model.VideoVO;






//@WebServlet("/front-end/itinerary/ItineraryServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize =1000 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024 * 1024)
public class ItineraryServlet extends HttpServlet {
	private static final long serialVersionUID = -3501163087212512005L;

public ItineraryServlet(){
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
				 if( las.getLandscape_add()!=null && las.getLandscape_add().substring(0,3).equals(landscape_city)){
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
			JSONArray array = new JSONArray();
			LandscapeService landscapeService =new LandscapeService();
//			LandscapeVO landscapeVO = new LandscapeVO();
			JedisQuestionPool jedisQuestionPool = new JedisQuestionPool();
			JedisQuestionVO jedisQuestionVO =new JedisQuestionVO();
			jedisQuestionVO= jedisQuestionPool.getAll(itinerary_no);
			

//			List<PictureVO> list2 = pictureService.getOnePic(itinerary_no);
			int i =1;
			for (Itinerary_detailVO itinerary_detailVO : list) {
				JSONObject obj = new JSONObject();
				System.out.print(itinerary_detailVO.getLandscape_no() + ",");		
				System.out.print(itinerary_detailVO.getItinerary_content() + ",");
				System.out.print(pictureService.getTheOnePic(itinerary_no, itinerary_detailVO.getLandscape_no()).getPic_no() + ",");
				try {
					if(i++ ==1) {
						obj.put("question",jedisQuestionVO.getQusetion());
						obj.put("qusetionOption1",jedisQuestionVO.getQusetionOption1());
						obj.put("qusetionOption2",jedisQuestionVO.getQusetionOption2());
						obj.put("qusetionOption3",jedisQuestionVO.getQusetionOption3());
						obj.put("answer",jedisQuestionVO.getAnswer());
					}
					obj.put("pic_no", pictureService.getTheOnePic(itinerary_no, itinerary_detailVO.getLandscape_no()).getPic_no());
					obj.put("landscape_no", itinerary_detailVO.getLandscape_no());
					obj.put("itinerary_content", itinerary_detailVO.getItinerary_content());
					obj.put("landscape_lat", landscapeService.getOneLas(itinerary_detailVO.getLandscape_no()).getLandscape_lat());
					obj.put("landscape_lng", landscapeService.getOneLas(itinerary_detailVO.getLandscape_no()).getLandscape_lng());
					obj.put("landscape_name", landscapeService.getOneLas(itinerary_detailVO.getLandscape_no()).getLandscape_name());
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
						System.out.print(landscapeVO.getLandscape_name() + ",");	
						obj.put("pic_no",pictureVO.getPic_no());
						obj.put("landscape_name",landscapeVO.getLandscape_name());
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
		
		
		if ("itineraryStart".equals(action)) {
			String itineraryStart_itinerary_no =req.getParameter("itineraryStart_itinerary_no");
			String mem_no =req.getParameter("mem_no");
			ItineraryStartService itineraryStartService= new ItineraryStartService();
			itineraryStartService.addIti(itineraryStart_itinerary_no,mem_no);
			System.out.println(itineraryStart_itinerary_no);
			System.out.println(mem_no);
			JSONObject obj = new JSONObject();	
			try {
				obj.put("OK", "success");
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
		
		if ("search".equals(action)) {
			String searchText =req.getParameter("searchText");
			System.out.println(searchText);
			
			Itinerary_detailService itinerary_detailService= new Itinerary_detailService();
			ItineraryService itineraryService= new ItineraryService();
			LandscapeService landscapeService = new LandscapeService();
			
			List<LandscapeVO> landscapelist = landscapeService.getAll();
			List<Itinerary_detailVO> itinerary_detailList = itinerary_detailService.getAll();
//			List<ItineraryVO> ItineraryList = itineraryService.getAll();
			JSONArray array = new JSONArray();
			for (LandscapeVO landscapeVO : landscapelist) {				
				if(landscapeVO.getLandscape_name().contains(searchText)) {
					
					System.out.println(landscapeVO.getLandscape_name());
					for(Itinerary_detailVO itinerary_detailVO:itinerary_detailList) {
						if(itinerary_detailVO.getLandscape_no().equals(landscapeVO.getLandscape_no())) {
							
							System.out.println(itinerary_detailVO.getItinerary_no());
//							Itinerary_detailService itinerary_detailService2= new Itinerary_detailService();
							List<Itinerary_detailVO> itinerary_detailList2 = itinerary_detailService.getLands(itinerary_detailVO.getItinerary_no());
							try {
								JSONObject obj = new JSONObject();
								obj.put("Itinerary_no", itinerary_detailVO.getItinerary_no());
								int i=1;
								for(Itinerary_detailVO itinerary_detailVO2:itinerary_detailList2) {									
									try {			
										System.out.println(itinerary_detailVO2.getLandscape_no());
										obj.put("landscape_no"+ i, itinerary_detailVO2.getLandscape_no());
										obj.put("landscape_names"+ i++, landscapeService.getOneLas(itinerary_detailVO2.getLandscape_no()).getLandscape_name());
										
//										obj.put("landscape_name", landscapeVO.getLandscape_name());
										obj.put("mem_no", itineraryService.getOne(itinerary_detailVO.getItinerary_no()).getMem_no());
									} catch (JSONException e) {
										e.printStackTrace();
									}
										array.put(obj);
								}

								
							} catch (JSONException e) {
								e.printStackTrace();
							}
							
						}					
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
		
		
		if ("videoShow".equals(action)) {
			String mem_no =req.getParameter("mem_no");
			VideoService videoService= new VideoService();
			List<VideoVO> list = videoService.getMemALL(mem_no);
			System.out.println(mem_no);
			JSONArray array = new JSONArray();			
			for(VideoVO videoVO:list) {
				JSONObject obj = new JSONObject();	
				try {
					obj.put("vdo_no", videoVO.getVdo_no());
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
		
		
		
		
		if ("addPoint".equals(action)) {
			HttpSession session = req.getSession();
			System.out.println("add測試點");
			MemberVO member = (MemberVO) session.getAttribute("memberVO");
			int olpoint =member.getMem_amo();
			System.out.println(member.getMem_amo());
			String pointafter = req.getParameter("pointafter");
			
			MemberService ms = new MemberService();
		    int point = Integer.parseInt(pointafter);
		    System.out.println(point);
		    olpoint=olpoint+point;
		    ms.updateAboutMenAmo(member.getMem_no(), olpoint);
		    member.setMem_amo(olpoint);
		    session.setAttribute("memberVO", member);
		    JSONObject obj = new JSONObject();	
				
				try {
					obj.put("ok", "ok");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
//		}
		}
		
		
//		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemberVO memberVO =(MemberVO)session.getAttribute("memberVO");
		String mem_no = memberVO.getMem_no();
		System.out.println(mem_no);
		String action = req.getParameter("action");
		
		
		
        if ("insert".equals(action)) {
        		

        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			
			JedisQuestionVO jedisQuestionVO =new JedisQuestionVO();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/		
				String qustion = req.getParameter("qustion").trim();
				jedisQuestionVO.setQusetion(qustion);
				if (qustion == null || qustion.trim().length() == 0) {
					errorMsgs.add("自訂問題題目勿空白");
				}
					
				
				String qustionOption1 = req.getParameter("qustionOption1");				
				if(qustionOption1 == null || qustionOption1.trim().length() == 0)
					errorMsgs.add("選項1請勿空白");
				
				String qustionOption2 = req.getParameter("qustionOption2");				
				if(qustionOption2 == null || qustionOption2.trim().length() == 0)
					errorMsgs.add("選項2請勿空白");
				
				String qustionOption3 = req.getParameter("qustionOption3");				
				if(qustionOption3 == null || qustionOption3.trim().length() == 0)
					errorMsgs.add("選項3請勿空白");
				
				
				 System.out.println("這嗎?1");
				 
				String checkboxes[]= req.getParameterValues("checkboxes");	
				if (checkboxes == null) {
					errorMsgs.add("請勾選正確解答");
				}

				System.out.println("這嗎?2");
				jedisQuestionVO.setQusetion(qustion);
				jedisQuestionVO.setQusetionOption1(qustionOption1);
				jedisQuestionVO.setQusetionOption2(qustionOption2);
				jedisQuestionVO.setQusetionOption3(qustionOption3);
				jedisQuestionVO.setAnswer(checkboxes[0]);
//				JedisQuestionPool jedisQuestionPool = new JedisQuestionPool();
//				jedisQuestionPool.insert(jedisQuestionVO);

				System.out.println("answer="+checkboxes[0]);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("jedisQuestionVO", jedisQuestionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/itinerary/itineraryUpload.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println("這嗎?3");
				List<byte[]> picbufs = new ArrayList<>();
	        	List<byte[]> vdobufs = new ArrayList<>();
	        	Collection<Part> parts = req.getParts();
	    		for (Part part : parts) {			
	    			String filename = part.getSubmittedFileName();   //*********Servlet 3.1 才能用    		    		
	    			if (filename!=null && part.getContentType()!=null && filename.length()!=0) {				
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
	    		String landscape_no[] = req.getParameterValues("landscape_no");
	    		String landscape_city = req.getParameter("landscape_city").trim();
				System.out.println(landscape_no.length);
				System.out.println(landscape_city);
	    		
				/***************************2.開始新增資料***************************************/
				
	    		ItineraryService itinerarySvc = new ItineraryService();		
				itinerarySvc.addIti(mem_no,"I1",landscape_no, picbufs,vdobufs,qustion,qustionOption1,qustionOption2,qustionOption3,checkboxes[0]);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/landscape/landscape.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
				
				
				res.sendRedirect(req.getContextPath()+"/front-end/home/TeamAce.jsp");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/itinerary/itineraryUpload.jsp");
				failureView.forward(req, res);
			}
        	
        	
		
				
//				JedisQuestionPool jedisQuestionPool = new JedisQuestionPool();
//				JedisQuestionVO jedisQuestionVO =null;
				
//				String qustionOption[] = req.getParameterValues("qustionOption");
//				String qustion= req.getParameter("qustion");
//				String checkboxes[]= req.getParameterValues("checkboxes");
//				for(int i=0; i<checkboxes.length ;i++)
//	            {
//	                if(checkboxes[i] != null)
//	                {
//	                    System.out.print(checkboxes[i]);
//	                }          
//	            }
//				jedisQuestionVO.setQusetion(qustion);
//				jedisQuestionVO.setQusetionOption1(qustionOption[0]);
//				jedisQuestionVO.setQusetionOption2(qustionOption[1]);
//				jedisQuestionVO.setQusetionOption3(qustionOption[2]);
//				jedisQuestionVO.setAnswer(checkboxes);
//				for(int i=0;i<qustionOption.length;i++) {
//					jedisQuestionVO.setQusetionOption1(qusetionOption1);
//				}
				
//				jedisQuestionPool.insert(jedisQuestionVO);
//				String url =  "/front-end/home/TeamAce.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);								
				
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
