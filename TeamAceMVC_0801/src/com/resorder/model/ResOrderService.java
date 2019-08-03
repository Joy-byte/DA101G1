package com.resorder.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.guidetour.model.GuideTourService;

public class ResOrderService {
	private ResOrderDAO_interface dao;

	public ResOrderService() {
		dao = new ResOrderDAO();
	}

	public ResOrderVO addResOrder(String mem_no, String guide_no, String guideTour_no, Date resOrder_date,
			Integer resOrder_peo, String resOrder_msg, String resOrder_status, String resOrder_joinpeo,
			Double resOrder_rate, String resOrder_cancel, String resOrder_review, Date resOrder_time) {

		ResOrderVO resOrderVO = new ResOrderVO();
		resOrderVO.setMem_no(mem_no);
		resOrderVO.setGuide_no(guide_no);
		resOrderVO.setGuideTour_no(guideTour_no);
		resOrderVO.setResOrder_date(resOrder_date);
		resOrderVO.setResOrder_peo(resOrder_peo);
		resOrderVO.setResOrder_msg(resOrder_msg);
		resOrderVO.setResOrder_status(resOrder_status);
		resOrderVO.setResOrder_joinpeo(resOrder_joinpeo);
		resOrderVO.setResOrder_rate(resOrder_rate);
		resOrderVO.setResOrder_cancel(resOrder_cancel);
		resOrderVO.setResOrder_review(resOrder_review);
		resOrderVO.setResOrder_time(resOrder_time);

		dao.insert(resOrderVO);

		return resOrderVO;
	}

	public ResOrderVO updateResOrder(String resOrder_no, String mem_no, String guide_no, String guideTour_no,
			Date resOrder_date, Integer resOrder_peo, String resOrder_msg, String resOrder_status,
			String resOrder_joinpeo, Double resOrder_rate, String resOrder_cancel, String resOrder_review,
			Date resOrder_time) {
		ResOrderVO resOrderVO2 = new ResOrderVO();
		resOrderVO2.setResOrder_no(resOrder_no);
		resOrderVO2.setMem_no(mem_no);
		resOrderVO2.setGuide_no(guide_no);
		resOrderVO2.setGuideTour_no(guideTour_no);
		resOrderVO2.setResOrder_date(resOrder_date);
		resOrderVO2.setResOrder_peo(resOrder_peo);
		resOrderVO2.setResOrder_msg(resOrder_msg);
		resOrderVO2.setResOrder_status(resOrder_status);
		resOrderVO2.setResOrder_joinpeo(resOrder_joinpeo);
		resOrderVO2.setResOrder_rate(resOrder_rate);
		resOrderVO2.setResOrder_cancel(resOrder_cancel);
		resOrderVO2.setResOrder_review(resOrder_review);
		resOrderVO2.setResOrder_time(resOrder_time);
		dao.update(resOrderVO2);

		return resOrderVO2;
	}

	public void deleteResOrder(String resOrder_no) {
		dao.delete(resOrder_no);
	}

	public ResOrderVO getOneResOrder(String resOrder_no) {
		return dao.findByPrimaryKey(resOrder_no);
	}

	public List<ResOrderVO> getAll() {
		return dao.getAll();
	}

	// 抓行程評論用
	public List<ResOrderVO> getTourResOrders(String guideTour_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if (vo.getGuideTour_no().equals(guideTour_no) && vo.getResOrder_rate() != 0) {
				list.add(vo);
			}
		}
		return list;
	}

	// 預約中訂單
	public List<ResOrderVO> getIng(String mem_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R1".equals(vo.getResOrder_status()) && vo.getMem_no().equals(mem_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	public List<ResOrderVO> getGuideIng(String guide_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R1".equals(vo.getResOrder_status()) && vo.getGuide_no().equals(guide_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	// 完成的訂單
	public List<ResOrderVO> getFinish(String mem_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R2".equals(vo.getResOrder_status()) && vo.getMem_no().equals(mem_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	public List<ResOrderVO> getGuideFinish(String guide_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R2".equals(vo.getResOrder_status()) && vo.getGuide_no().equals(guide_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	// 取消的訂單
	public List<ResOrderVO> getCancel(String mem_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R3".equals(vo.getResOrder_status()) && vo.getMem_no().equals(mem_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	public List<ResOrderVO> getGuideCancel(String guide_no) {
		List<ResOrderVO> list = new LinkedList<>();
		List<ResOrderVO> list2 = dao.getAll();
		for (ResOrderVO vo : list2) {
			if ("R3".equals(vo.getResOrder_status()) && vo.getGuide_no().equals(guide_no)) {
				list.add(vo);
			}
		}
		return list;
	}

	// 不可共遊&可共遊但當天人數總和已達上限(不可預約)
	public Map<String, Integer> getGrayDay(String guideTour_no) {
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> mapRJ2 = new HashMap<>();
		List<ResOrderVO> list2 = dao.getAll();
		GuideTourService svc = new GuideTourService();
		Integer maxPeo = svc.getOneGuideTour(guideTour_no).getGuideTour_peo();
		for (ResOrderVO vo : list2) {
			if ("R1".equals(vo.getResOrder_status())
					&& vo.getResOrder_date().getTime() - new java.util.Date().getTime() > 0
					&& vo.getGuideTour_no().equals(guideTour_no)) {
				if ("RJ1".equals(vo.getResOrder_joinpeo())) {
					map.put(vo.getResOrder_date().toString(), vo.getResOrder_peo());
				} else {
					if (mapRJ2.containsKey(vo.getResOrder_date().toString())) {
						Integer num = (Integer) mapRJ2.get(vo.getResOrder_date().toString()) + vo.getResOrder_peo();
						mapRJ2.put(vo.getResOrder_date().toString(), num);
					} else {
						mapRJ2.put(vo.getResOrder_date().toString(), vo.getResOrder_peo());
					}
				}
			}
		}
		for (String date : mapRJ2.keySet()) {
			if (mapRJ2.get(date) >= maxPeo) {
				map.put(date, mapRJ2.get(date));
			}
		}
		return map;
	}

	// 可共遊且人數未達上限(可預約)
	public Map<String, Integer> getGreenDay(String guideTour_no) {
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> mapRJ2 = new HashMap<>();
		List<ResOrderVO> list = dao.getAll();
		GuideTourService svc = new GuideTourService();
		Integer maxPeo = svc.getOneGuideTour(guideTour_no).getGuideTour_peo();
		for (ResOrderVO vo : list) {
			if ("R1".equals(vo.getResOrder_status())
					&& vo.getResOrder_date().getTime() - new java.util.Date().getTime() > 0
					&& vo.getGuideTour_no().equals(guideTour_no)) {
				if ("RJ2".equals(vo.getResOrder_joinpeo())) {
					if (mapRJ2.containsKey(vo.getResOrder_date().toString())) {
						Integer num = (Integer) mapRJ2.get(vo.getResOrder_date().toString()) + vo.getResOrder_peo();
						mapRJ2.put(vo.getResOrder_date().toString(), num);
					} else {
						mapRJ2.put(vo.getResOrder_date().toString(), vo.getResOrder_peo());
					}
				}
			}
		}
		for (String date : mapRJ2.keySet()) {
			if (mapRJ2.get(date) < maxPeo) {
				map.put(date, mapRJ2.get(date));
			}
		}
		return map;
	}

	// 取得其他行程有人預約的日期(
	public Set<String> getOtherDay(String guideTour_no, String guide_no) {
		Set<String> otherDay = new HashSet<>();
		List<ResOrderVO> list = dao.getAll();
		for (ResOrderVO vo : list) {
			if ("R1".equals(vo.getResOrder_status())
					&& vo.getResOrder_date().getTime() - new java.util.Date().getTime() > 0
					&& vo.getGuide_no().equals(guide_no) && !vo.getGuideTour_no().equals(guideTour_no)) {
				otherDay.add(vo.getResOrder_date().toString());
			}
		}
		return otherDay;
	}

	// test
	public static void main(String[] args) {
		ResOrderService ros = new ResOrderService();

		// 測試getGrayDay()
//		Map<String,Integer> mapGray = new HashMap<>();
//		Map<String,Integer> mapGreen = new HashMap<>();
//		mapGray = ros.getGrayDay("GT00001");
//		mapGreen = ros.getGreenDay("GT00001");
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date = new java.util.Date();
//		StringBuilder str= new StringBuilder();
//		str.append("{\"start\":\"1970-01-01\",\"end\":\""+Date.valueOf(sdf.format(date))+"\",\"rendering\":\"background\",\"color\":\"#bac4ce\"},");
//		for(String dateGray : mapGray.keySet()) {
//			str.append("{\"start\":\""+dateGray+"\",\"rendering\":\"background\",\"color\":\"#bac4ce\"},");
//			str.append("{\"start\":\""+dateGray+"\",\"title\":\"已預約  "+mapGray.get(dateGray)+"  人\",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"},");
//		}
//		for(String dateGreen : mapGreen.keySet()){
//			str.append("{\"start\":\""+dateGreen+"\",\"rendering\":\"background\"},");
//			str.append("{\"start\":\""+dateGreen+"\",\"title\":\"已預約  "+mapGreen.get(dateGreen)+"  人\",\"backgroundColor\":\"#b6e2db\",\"borderColor\":\"#b6e2db\"},");
//		}
//		String str2 = "["+str+"]";
//		System.out.println(str2);

		// 測試getGreenDay()
//		Map<Date,Integer> map = new HashMap<>();
//		map = ros.getGreenDay("GT00001");
//		System.out.println("以下可預約");
//		for(Date date : map.keySet()) {
//			System.out.println(date+" 預約"+map.get(date));
//		}
//		Map<String,Integer> mapGray = new HashMap<>();
//		Set<String> otherDay = ros.getOtherDay("GT00001","GU00001");
//		mapGray = ros.getGrayDay("GT00001");
//		Set<String> set = new HashSet<>(mapGray.keySet());
//		set.addAll(otherDay);
//		for(String date:set) {
//			System.out.println(date);
//		}

		//測試mapGreen
		Map<String,Integer> mapGreen = new HashMap<>();
		mapGreen = ros.getGreenDay("GT00019");
		JSONObject json = new JSONObject(mapGreen);
		System.out.println(json);
		
		// add
//		ros.addResOrder("MB00003", "GU00002", "GT00002", java.sql.Date.valueOf("2019-06-20"), 3, "msg", "R1", "RJ1",4.5,"","",java.sql.Date.valueOf(LocalDate.now()));

		// update
//		ros.updateResOrder("RO00004","MB00003", "GU00002", "GT00002", java.sql.Date.valueOf("2019-06-20"), 5, "msg121212", "R1", "RJ2",4.5,"","",java.sql.Date.valueOf("2019-07-01"));

		// delete
//		ros.deleteResOrder("RO00005");

		// getOne
//		ResOrderVO resOrderVO3 = ros.getOneResOrder("RO00001");
//		System.out.print(resOrderVO3.getResOrder_no() + ",");
//		System.out.print(resOrderVO3.getMem_no() + ",");
//		System.out.print(resOrderVO3.getGuide_no() + ",");
//		System.out.print(resOrderVO3.getGuideTour_no() + ",");
//		System.out.print(resOrderVO3.getResOrder_date().toString() + ",");
//		System.out.print(resOrderVO3.getResOrder_peo() + ",");
//		System.out.print(resOrderVO3.getResOrder_msg() + ",");
//		System.out.print(resOrderVO3.getResOrder_status()+",");
//		System.out.print(resOrderVO3.getResOrder_rate()+",");
//		System.out.print(resOrderVO3.getResOrder_cancel()+",");
//		System.out.print(resOrderVO3.getResOrder_review()+",");
//		System.out.print(resOrderVO3.getResOrder_time()+",");
//		System.out.print(resOrderVO3.getResOrder_joinpeo());
//		System.out.println();

		// getAll
//		List<ResOrderVO> list = ros.getAll();
//		for (ResOrderVO resOrderVO4 : list) {
//			System.out.print(resOrderVO4.getResOrder_no() + ",");
//			System.out.print(resOrderVO4.getMem_no() + ",");
//			System.out.print(resOrderVO4.getGuide_no() + ",");
//			System.out.print(resOrderVO4.getGuideTour_no() + ",");
//			System.out.print(resOrderVO4.getResOrder_date() + ",");
//			System.out.print(resOrderVO4.getResOrder_peo() + ",");
//			System.out.print(resOrderVO4.getResOrder_msg() + ",");
//			System.out.print(resOrderVO4.getResOrder_status()+",");
//			System.out.print(resOrderVO4.getResOrder_joinpeo()+",");
//			System.out.print(resOrderVO4.getResOrder_rate()+",");
//			System.out.print(resOrderVO4.getResOrder_cancel()+",");
//			System.out.print(resOrderVO4.getResOrder_review()+",");
//			System.out.print(resOrderVO4.getResOrder_time()+",");
//			System.out.println();
//		}

//		List<ResOrderVO> list = ros.getIng("MB00001");
//		for(ResOrderVO vo : list) {
//			System.out.println(vo.getResOrder_no());
//			long time = vo.getResOrder_date().getTime();
//			long now = System.currentTimeMillis();
//			System.out.println("date:"+time);
//			System.out.println("now:"+now);
//			
//		}

//		List<ResOrderVO> list = ros.getFinish("MB00001");
//		for(ResOrderVO vo : list) {
//			System.out.println(vo.getResOrder_no());
//		}

//		List<ResOrderVO> list = ros.getCancel("MB00001");
//		for(ResOrderVO vo : list) {
//			System.out.println(vo.getResOrder_no());
//		}
	}
}
