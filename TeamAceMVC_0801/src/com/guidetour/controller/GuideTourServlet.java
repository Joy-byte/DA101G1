package com.guidetour.controller;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.*;
import com.guidetour.model.*;
import com.mem.model.*;
import com.gtlist.model.*;
import com.gtclist.model.*;
import com.guide.model.*;
import com.landscape.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 500 * 1024
		* 1024)
public class GuideTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("readMore".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String guideTour_no = req.getParameter("guideTour_no");

			/*************************** 2.開始查詢資料 ****************************************/
			GuideTourService guideTourSvc = new GuideTourService();
			GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
			GuideService guideSvc = new GuideService();
			GuideVO guideVO = guideSvc.getOneGuide(guideTourVO.getGuide_no());
			MemberService memSvc = new MemberService();
			MemberVO guideMemVO = memSvc.getOneMember(guideVO.getMem_no());

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("guideTourVO", guideTourVO);
			req.setAttribute("guideVO", guideVO);
			req.setAttribute("guideMemVO", guideMemVO);

			String url = "/front-end/guidetour/GuideTourDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);

			successView.forward(req, res);
		}

		if ("getGuideTour".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String city = req.getParameter("city");
				String category = req.getParameter("category");
				String search = req.getParameter("searchString");
				String[] searchArray = null;
				if(search != null) {
					searchArray = search.split(" ");
				}

				/*************************** 2.開始查詢資料 ****************************************/
				JSONArray array = new JSONArray();
				GuideTourService guideTourSvc = new GuideTourService();
				MemberService memSvc = new MemberService();
				GuideService guideSvc = new GuideService();
				GTListService gtListSvc = new GTListService();
				GTCListService gtcListSvc = new GTCListService();
				LandscapeService landscapeSvc = new LandscapeService();
				List<GuideTourVO> list = new ArrayList<>();
				List<GuideTourVO> listAll = guideTourSvc.getAll();
				if (!"全部".equals(city) && !"全部".equals(category)) {
					for (GuideTourVO vo : listAll) {
						if (vo.getGuideTour_area().equals(city)) {
							List<GTCListVO> gtcList = gtcListSvc.getCategoryByGTno(vo.getGuideTour_no());
							for (GTCListVO gtcListVO : gtcList) {
								if (gtcListVO.getCategory().equals(category)) {
									list.add(vo);
									break;
								}
							}
						}
					}
				} else if (!"全部".equals(city)) {
					list = listAll.stream().filter(ob -> ob.getGuideTour_area().equals(city))
							.collect(Collectors.toList());
				} else if (!"全部".equals(category)) {
					for (GuideTourVO vo : listAll) {
						List<GTCListVO> gtcList = gtcListSvc.getCategoryByGTno(vo.getGuideTour_no());
						for (GTCListVO gtcListVO : gtcList) {
							if (gtcListVO.getCategory().equals(category)) {
								list.add(vo);
								break;
							}
						}
					}
				} else {
					list = listAll;
				}
				//文字輸入查詢
				List<GuideTourVO> list2 = new ArrayList<>();
				if(search!=null) {
					for(GuideTourVO vo : list) {
						StringBuilder str = new StringBuilder();
						str.append(vo.getGuideTour_name());
						List<GTListVO> landList = gtListSvc.getGTListByGTNo(vo.getGuideTour_no());
						for(GTListVO landVO : landList) {
							String name = landscapeSvc.getOneLas(landVO.getLandscape_no()).getLandscape_name();
							str.append(name);
						}
						String string = str.toString();
						for(int i=0;i<searchArray.length;i++) {
							CharSequence cs = searchArray[i];
							if(!string.contains(cs)) {
								break;
							}
							if(i==searchArray.length-1) {
								list2.add(vo);
							}
						}
					}
					list = list2;
				}

				if ("order_rate".equals(req.getParameter("orderby"))) {
					list = list.stream().sorted(Comparator.comparing(GuideTourVO::getGuideTour_rate).reversed())
							.collect(Collectors.toList());
				}
				if ("order_new".equals(req.getParameter("orderby"))) {
					list = list.stream().sorted(Comparator.comparing(GuideTourVO::getGuideTour_no).reversed())
							.collect(Collectors.toList());
				}

				for (GuideTourVO vo : list) {
					JSONObject obj = new JSONObject();
					JSONArray landArray = new JSONArray();
					JSONArray catArray = new JSONArray();
					String guideTour_no = vo.getGuideTour_no();
					String guide_no = vo.getGuide_no();
					String guide_memno = guideSvc.getOneGuide(guide_no).getMem_no();
					obj.put("guideTourNo", guideTour_no);
					obj.put("guideTourRate", guideTourSvc.getGuideTourRate(guideTour_no));
					obj.put("guideMemName", memSvc.getOneMember(guide_memno).getMem_name());
					obj.put("guideMemNo", guide_memno);
					obj.put("guideNo", guide_no);
					obj.put("guideTourName", vo.getGuideTour_name());
					List<GTListVO> landList = gtListSvc.getGTListByGTNo(guideTour_no);
					for (GTListVO gtListVO : landList) {
						String landscapeName = landscapeSvc.getOneLas(gtListVO.getLandscape_no()).getLandscape_name();
						landArray.put(landscapeName);
					}
					obj.put("landscape", landArray);
					List<GTCListVO> catList = gtcListSvc.getCategoryByGTno(guideTour_no);
					for (GTCListVO gtcListVO : catList) {
						String categoryName = (gtcListVO.getCategory());
						catArray.put(categoryName);
					}
					obj.put("category", catArray);
					array.put(obj);
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
		PrintWriter out = res.getWriter();

		if ("delete".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String guideTour_no = req.getParameter("guideTour_no");

			/*************************** 2.開始修改資料 ****************************************/
			GuideTourService guideTourSvc = new GuideTourService();
			GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
			guideTourSvc.updateGuideTour(guideTour_no, guideTourVO.getGuide_no(), guideTourVO.getGuideTour_area(),
					guideTourVO.getGuideTour_detail(), guideTourVO.getGuideTour_name(), guideTourVO.getGuideTour_peo(),
					guideTourVO.getGuideTour_pic(), "GT2", guideTourVO.getGuideTour_rate());

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String location = req.getContextPath() + "/front-end/guide/GuideTour.jsp";
//			"/front-end/guidetour/GuideTour.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
			res.sendRedirect(location);
		}

		if ("insert".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			try {
				String guideTour_area = req.getParameter("guideTour_area");
				Integer guideTour_peo = Integer.parseInt(req.getParameter("guideTour_peo"));
				String guideTour_detail = req.getParameter("guideTour_detail");
				String guide_no = req.getParameter("guide_no");
				String guideTour_name = req.getParameter("guideTour_name");
				String jsonStr = req.getParameter("jsonStr");
				String[] categoryArray = req.getParameterValues("category");
				JSONArray array = new JSONArray();

				array = new JSONArray(jsonStr);
				byte[] guideTour_pic = null;
				Part part = req.getPart("guideTour_pic");
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					guideTour_pic = new byte[in.available()];
					in.read(guideTour_pic);
					in.close();
				}
				/*************************** 2.開始新增資料 ****************************************/
				GuideTourService guideTourSvc = new GuideTourService();
				GTListService gtlistSvc = new GTListService();
				GTCListService gtcListSvc = new GTCListService();
				GuideTourVO guideTourVO = guideTourSvc.addGuideTour(guide_no, guideTour_area, guideTour_detail,
						guideTour_name, guideTour_peo, guideTour_pic, "GT1", new Double(0));
				String guideTour_no = guideTourVO.getGuideTour_no();
				// insert gtlist
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					String landscape_no = (String) object.get("landscape_no");
					Integer landscape_min = 0;
					Integer landscape_hr = 0;
					String landscape_trans = null;
					if (!"".equals((String) object.get("landscape_trans"))
							&& (String) object.get("landscape_trans") != null) {
						System.out.println((String) object.get("landscape_min"));
						landscape_min = Integer.parseInt((String) object.get("landscape_min"));
						landscape_hr = Integer.parseInt((String) object.get("landscape_hr"));
						landscape_trans = (String) object.get("landscape_trans");
					}
					gtlistSvc.addGTList(guideTour_no, landscape_no, null, landscape_trans, landscape_hr, landscape_min,
							i + 1);
				}
				// insert gtclist
				for (int i = 0; i < categoryArray.length; i++) {
					gtcListSvc.addGTCList(guideTour_no, categoryArray[i]);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String location = req.getContextPath() + "/front-end/guide/GuideTour.jsp";
			res.sendRedirect(location);
		}

		if ("update".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			try {
				String guideTour_area = req.getParameter("guideTour_area");
				Integer guideTour_peo = Integer.parseInt(req.getParameter("guideTour_peo"));
				String guideTour_detail = req.getParameter("guideTour_detail");
				String guide_no = req.getParameter("guide_no");
				String guideTour_no = req.getParameter("guideTour_no");
				String guideTour_name = req.getParameter("guideTour_name");
				String jsonStr = req.getParameter("jsonStr");
				String[] categoryArray = req.getParameterValues("category");
				JSONArray array = new JSONArray();

				GuideTourService guideTourSvc = new GuideTourService();
				array = new JSONArray(jsonStr);
				byte[] guideTour_pic = null;
				Part part = req.getPart("guideTour_pic");
				if (part.getSize() != 0) {
					InputStream in = part.getInputStream();
					guideTour_pic = new byte[in.available()];
					in.read(guideTour_pic);
					in.close();
				} else {
					guideTour_pic = guideTourSvc.getOneGuideTour(guideTour_no).getGuideTour_pic();
				}
				/*************************** 2.開始新增資料 ****************************************/
				GTListService gtlistSvc = new GTListService();
				GTCListService gtcListSvc = new GTCListService();
				GuideTourVO guideTourVO = guideTourSvc.getOneGuideTour(guideTour_no);
				// update guideTour
				guideTourSvc.updateGuideTour(guideTour_no, guide_no, guideTour_area, guideTour_detail, guideTour_name,
						guideTour_peo, guideTour_pic, guideTourVO.getGuideTour_status(), guideTourVO.getGuideTour_rate());
				
				// insert gtlist
				List<GTListVO> gtList =  gtlistSvc.getGTListByGTNo(guideTour_no);
				for(GTListVO vo : gtList) {
					String landscape_no = vo.getLandscape_no();
					gtlistSvc.deleteGTList(guideTour_no, landscape_no);
				}
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					String landscape_no = (String) object.get("landscape_no");
					Integer landscape_min = 0;
					Integer landscape_hr = 0;
					String landscape_trans = null;
					if (!"".equals((String) object.get("landscape_trans"))
							&& (String) object.get("landscape_trans") != null) {
						System.out.println((String) object.get("landscape_min"));
						landscape_min = Integer.parseInt((String) object.get("landscape_min"));
						landscape_hr = Integer.parseInt((String) object.get("landscape_hr"));
						landscape_trans = (String) object.get("landscape_trans");
					}
					gtlistSvc.addGTList(guideTour_no, landscape_no, null, landscape_trans, landscape_hr, landscape_min,
							i + 1);
				}
				// insert gtclist
				List<GTCListVO> gtcList =  gtcListSvc.getCategoryByGTno(guideTour_no);
				for(GTCListVO vo : gtcList) {
					String category = vo.getCategory();
					gtcListSvc.deleteGTCList(guideTour_no, category);
				}
				for (int i = 0; i < categoryArray.length; i++) {
					gtcListSvc.addGTCList(guideTour_no, categoryArray[i]);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			String location = req.getContextPath() + "/front-end/guide/GuideTour.jsp";
			res.sendRedirect(location);
		}

	}

}
