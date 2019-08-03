package com.guidetour.model;

import java.text.DecimalFormat;
import java.util.*;


import com.guide.model.GuideService;
import com.resorder.model.ResOrderService;
import com.resorder.model.ResOrderVO;

public class GuideTourService {
	private GuideTourDAO_interface dao;

	public GuideTourService() {
		dao = new GuideTourDAO();
	}

	public GuideTourVO addGuideTour(String guide_no, String guideTour_area, String guideTour_detail,
			String guideTour_name, Integer guideTour_peo, byte[] guideTour_pic, String guideTour_status,
			Double guideTour_rate) {
		GuideTourVO guideTourVO1 = new GuideTourVO();
		guideTourVO1.setGuide_no(guide_no);
		guideTourVO1.setGuideTour_area(guideTour_area);
		guideTourVO1.setGuideTour_detail(guideTour_detail);
		guideTourVO1.setGuideTour_name(guideTour_name);
		guideTourVO1.setGuideTour_peo(guideTour_peo);
		guideTourVO1.setGuideTour_pic(guideTour_pic);
		guideTourVO1.setGuideTour_status(guideTour_status);
		guideTourVO1.setGuideTour_rate(guideTour_rate);
		String guideTour_no = dao.insert(guideTourVO1);
		guideTourVO1.setGuideTour_no(guideTour_no);
		return guideTourVO1;
	}

	public GuideTourVO updateGuideTour(String guideTour_no, String guide_no, String guideTour_area,
			String guideTour_detail, String guideTour_name, Integer guideTour_peo, byte[] guideTour_pic,
			String guideTour_status, Double guideTour_rate) {
		GuideTourVO guideTourVO2 = new GuideTourVO();
		guideTourVO2.setGuideTour_no(guideTour_no);
		guideTourVO2.setGuide_no(guide_no);
		guideTourVO2.setGuideTour_area(guideTour_area);
		guideTourVO2.setGuideTour_detail(guideTour_detail);
		guideTourVO2.setGuideTour_name(guideTour_name);
		guideTourVO2.setGuideTour_peo(guideTour_peo);
		guideTourVO2.setGuideTour_pic(guideTour_pic);
		guideTourVO2.setGuideTour_status(guideTour_status);
		guideTourVO2.setGuideTour_rate(guideTour_rate);
		dao.update(guideTourVO2);

		return guideTourVO2;
	}

	public void deleteGuideTour(String guideTour_no) {
		dao.delete(guideTour_no);
	}

	public GuideTourVO getOneGuideTour(String guideTour_no) {
		return dao.findByPrimaryKey(guideTour_no);
	}

	public List<GuideTourVO> getAll() {
		GuideService svc = new GuideService();
		List<GuideTourVO> list = dao.getAll();
		List<GuideTourVO> list2= new ArrayList<>();
		for(GuideTourVO vo : list) {
			if("GN1".equals(svc.getOneGuide(vo.getGuide_no()).getGuide_status())) {
				list2.add(vo);
			}
		}
		Collections.reverse(list2);
		return list2;
	}

	public List<GuideTourVO> getGuideTour(String guide_no) {
		List<GuideTourVO> list = new ArrayList<>();
		List<GuideTourVO> AllList = dao.getAll();
		for (GuideTourVO guideTourVO : AllList) {
			if (guideTourVO.getGuide_no().equals(guide_no)) {
				list.add(guideTourVO);
			}
		}
		return list;
	}
	//計算行程總評價
	public String getGuideTourRate(String guideTour_no) {
		ResOrderService resOrderSvc = new ResOrderService();
		List<ResOrderVO> list = resOrderSvc.getTourResOrders(guideTour_no);
		double sum = 0;
		for (ResOrderVO vo : list) {
			sum = sum + vo.getResOrder_rate();
		}
		DecimalFormat df = new DecimalFormat("0.0");
		if (list.size() != 0) {
			df.format(sum / list.size());
			return df.format(sum / list.size());
		} else {
			return "無";
		}

	}
	
	//計算旅伴已發布數量
	public int getUploadNum(String guide_no) {
		int num = 0;
		List<GuideTourVO> list = dao.getAll();
		for(GuideTourVO vo : list) {
			if(vo.getGuide_no().equals(guide_no)) {
				num++;
			}
		}
		return num;
	}
	//計算伴遊次數
	public int getFinishNum(String guide_no) {
		ResOrderService resOrderSvc = new ResOrderService();
		List<ResOrderVO> list = resOrderSvc.getGuideFinish(guide_no);
		return list.size();
	}

	// test
	public static void main(String[] args) {
		GuideTourService gts = new GuideTourService();

		// add
//		gts.addGuideTour("GU00002", "桃園市中壢區", "好好玩", "中央大學一日遊", 10, null, "GT1",4.0);

		// update
//		gts.updateGuideTour("GT00011", "GU00002", "桃園市中壢區", "好好玩!!!!", "中央大學一日遊", 10, null, "GT2",4.2);

		// delete
//		gts.deleteGuideTour("GT00004");

		// getOne
//		GuideTourVO guideTourVO3 = gts.getOneGuideTour("GT00001");
//		System.out.print(guideTourVO3.getGuideTour_no() + ",");
//		System.out.print(guideTourVO3.getGuide_no() + ",");
//		System.out.print(guideTourVO3.getGuideTour_area() + ",");
//		System.out.print(guideTourVO3.getGuideTour_detail() + ",");
//		System.out.print(guideTourVO3.getGuideTour_name() + ",");
//		System.out.print(guideTourVO3.getGuideTour_peo() + ",");
//		System.out.print(guideTourVO3.getGuideTour_pic() + ",");
//		System.out.print(guideTourVO3.getGuideTour_rate() + ",");
//		System.out.print(guideTourVO3.getGuideTour_status());
//		System.out.println();

		// getAll
//		List<GuideTourVO> list = gts.getAll();
//		for (GuideTourVO guideTourVO4 : list) {
//			System.out.print(guideTourVO4.getGuideTour_no() + ",");
//			System.out.print(guideTourVO4.getGuide_no() + ",");
//			System.out.print(guideTourVO4.getGuideTour_area() + ",");
//			System.out.print(guideTourVO4.getGuideTour_detail() + ",");
//			System.out.print(guideTourVO4.getGuideTour_name() + ",");
//			System.out.print(guideTourVO4.getGuideTour_peo() + ",");
//			System.out.print(guideTourVO4.getGuideTour_pic() + ",");
//			System.out.print(guideTourVO4.getGuideTour_rate() + ",");
//			System.out.print(guideTourVO4.getGuideTour_status());
//			System.out.println();
//		}
		
	}
}
