package com.itineraryStart.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;






//@WebServlet("/front-end/itinerary/ItineraryServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 900 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
public class ItineraryStartServlet extends HttpServlet {
	private static final long serialVersionUID = -3501163087212512005L;


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//Ajax
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");		
		System.out.println(action);
			
		if ("theTime".equals(action)) {
			String mem_no =req.getParameter("mem_no");
			System.out.println("MEMNO = " +mem_no);
			String itinerary_no =req.getParameter("itinerary_no");
			System.out.println("itinerary_no = " + itinerary_no);
			String time =req.getParameter("time");
			System.out.println("Time = " + time);
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("misj");	
			jedis.set(mem_no+":"+itinerary_no,time);	
//			String s = mem_no+":"+itinerary_no;
//			jedis.rpush(s, time);
			jedis.close();
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("misj", "misj");
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
//		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		
        
	}
				
	
}
