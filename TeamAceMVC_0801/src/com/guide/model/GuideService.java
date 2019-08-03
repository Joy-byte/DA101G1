package com.guide.model;

import java.util.List;

public class GuideService {
	private GuideDAO_interface dao;

	public GuideService() {
		dao = new GuideDAO();
	}

	public GuideVO addGuide(String mem_no, String guide_intro, byte[] guide_pic, byte[] guide_cover, String guide_hobby,
			String guide_status, Double guide_lng, Double guide_lat, String guide_area, Double guide_rate, String guide_stLang, String guide_ndLang) {
		GuideVO guideVO1 = new GuideVO();
		guideVO1.setMem_no(mem_no);
		guideVO1.setGuide_intro(guide_intro);
		guideVO1.setGuide_pic(guide_pic);
		guideVO1.setGuide_cover(guide_cover);
		guideVO1.setGuide_hobby(guide_hobby);
		guideVO1.setGuide_status(guide_status);
		guideVO1.setGuide_lng(guide_lng);
		guideVO1.setGuide_lat(guide_lat);
		guideVO1.setGuide_area(guide_area);
		guideVO1.setGuide_rate(guide_rate);
		guideVO1.setGuide_stLang(guide_stLang);
		guideVO1.setGuide_ndLang(guide_ndLang);
		dao.insert(guideVO1);

		return guideVO1;
	}

	public GuideVO updateGuide(String guide_no, String mem_no, String guide_intro, byte[] guide_pic, byte[] guide_cover,
			String guide_hobby, String guide_status, Double guide_lng, Double guide_lat, String guide_area,
			Double guide_rate, String guide_stLang, String guide_ndLang) {
		GuideVO guideVO2 = new GuideVO();
		guideVO2.setGuide_no(guide_no);
		guideVO2.setMem_no(mem_no);
		guideVO2.setGuide_intro(guide_intro);
		guideVO2.setGuide_pic(guide_pic);
		guideVO2.setGuide_cover(guide_cover);
		guideVO2.setGuide_hobby(guide_hobby);
		guideVO2.setGuide_status(guide_status);
		guideVO2.setGuide_lng(guide_lng);
		guideVO2.setGuide_lat(guide_lat);
		guideVO2.setGuide_area(guide_area);
		guideVO2.setGuide_rate(guide_rate);
		guideVO2.setGuide_stLang(guide_stLang);
		guideVO2.setGuide_ndLang(guide_ndLang);
		dao.update(guideVO2);

		return guideVO2;
	}
	
	public void updateGuideStatus(String guide_no, String guide_status) {
		dao.updateGuideStatus(guide_no, guide_status);
	}

	public void deleteGuide(String guide_no) {
		dao.delete(guide_no);
	}

	public GuideVO getOneGuide(String guide_no) {
		return dao.findByPrimaryKey(guide_no);
	}
	
	public GuideVO getOneGuideByMemNo(String mem_no) {
		return dao.findByMemNo(mem_no);
	}

	public List<GuideVO> getAll() {
		return dao.getAll();
	}
	

	// test
	public static void main(String[] args) {
		GuideService gs = new GuideService();
		// add
//		gs.addGuide("MB00004", "intro", null, null, "旅遊", "GN2", 121.2, 24.5555,"高雄市",2.0,"英文","");

		// update
//		gs.updateGuide("GU00004","MB00004", "intro2222", null, null, "旅遊 攝影", "GN1", 121.2222, 24.5555,"台南市",3.0,"英文","法文");
		
		//updateStatus
//		gs.updateGuideStatus("GU00003", "GN2");
		
		// delete
//		gs.deleteGuide("GU00004");

		// getOne
//		GuideVO vo = gs.getOneGuide("GU00001");
//		System.out.print(vo.getGuide_no() + ",");
//		System.out.print(vo.getMem_no() + ",");
//		System.out.print(vo.getGuide_intro() + ",");
//		System.out.print(vo.getGuide_pic() + ",");
//		System.out.print(vo.getGuide_hobby() + ",");
//		System.out.print(vo.getGuide_status() + ",");
//		System.out.print(vo.getGuide_lng()+",");
//		System.out.print(vo.getGuide_lat()+",");
//		System.out.print(vo.getGuide_area()+",");
//		System.out.print(vo.getGuide_rate()+",");
//		System.out.print(vo.getGuide_stLang()+",");
//		System.out.print(vo.getGuide_ndLang());
//		System.out.println();
		
		//getOne(by mem_no)
//		GuideVO vo = gs.getOneGuideByMemNo("MB00001");
//		System.out.print(vo.getGuide_no() + ",");
//		System.out.print(vo.getMem_no() + ",");
//		System.out.print(vo.getGuide_intro() + ",");
//		System.out.print(vo.getGuide_pic() + ",");
//		System.out.print(vo.getGuide_hobby() + ",");
//		System.out.print(vo.getGuide_status() + ",");
//		System.out.print(vo.getGuide_lng()+",");
//		System.out.print(vo.getGuide_lat()+",");
//		System.out.print(vo.getGuide_area()+",");
//		System.out.print(vo.getGuide_rate()+",");
//		System.out.print(vo.getGuide_stLang()+",");
//		System.out.print(vo.getGuide_ndLang());
//		System.out.println();

		// getAll
//		List<GuideVO> list = gs.getAll();
//		for (GuideVO vo2 : list) {
//			System.out.print(vo2.getGuide_no() + ",");
//			System.out.print(vo2.getMem_no() + ",");
//			System.out.print(vo2.getGuide_intro() + ",");
//			System.out.print(vo2.getGuide_pic() + ",");
//			System.out.print(vo2.getGuide_hobby() + ",");
//			System.out.print(vo2.getGuide_status() + ",");
//			System.out.print(vo2.getGuide_lng()+",");
//			System.out.print(vo2.getGuide_lat()+",");
//			System.out.print(vo2.getGuide_area()+",");
//			System.out.print(vo2.getGuide_rate()+",");
//			System.out.print(vo2.getGuide_stLang()+",");
//			System.out.print(vo2.getGuide_ndLang());
//			System.out.println();
//		}
	}
}
