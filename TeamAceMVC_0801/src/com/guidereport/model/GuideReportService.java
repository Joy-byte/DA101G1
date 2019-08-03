package com.guidereport.model;

import java.util.*;


public class GuideReportService {
	private GuideReportDAO_interface dao;

	public GuideReportService() {
		dao = new GuideReportDAO();
	}

	public GuideReportVO addGuideReport(String guideTour_no, String guideRep_reason, String guideRep_status, String mem_no) {
		GuideReportVO grVO1 = new GuideReportVO();
		grVO1.setGuideTour_no(guideTour_no);
		grVO1.setGuideRep_reason(guideRep_reason);
		grVO1.setGuideRep_status(guideRep_status);
		grVO1.setMem_no(mem_no);
		dao.insert(grVO1);

		return grVO1;
	}
	
	public GuideReportVO updateGuideReport(String guideRep_no,String guideTour_no, String guideRep_reason, String guideRep_status, String mem_no) {
		GuideReportVO grVO2 = new GuideReportVO();
		grVO2.setGuideRep_no(guideRep_no);
		grVO2.setGuideTour_no(guideTour_no);
		grVO2.setGuideRep_reason(guideRep_reason);
		grVO2.setGuideRep_status(guideRep_status);
		grVO2.setMem_no(mem_no);
		dao.update(grVO2);
		return grVO2;
	}

	public void deleteGuideReport(String guideRep_no) {
		dao.delete(guideRep_no);
	}
	
	public GuideReportVO getOneGuideReport(String guideRep_no) {
		return dao.findByPrimaryKey(guideRep_no);
	}
	
	public List<GuideReportVO> getAll(){
		return dao.getAll();
	}
	
	public List<GuideReportVO> getNotFinish(){
		List<GuideReportVO> list = dao.getAll();
		List<GuideReportVO> list2 = new ArrayList<>();
		for(GuideReportVO vo : list) {
			if("GR1".equals(vo.getGuideRep_status())){
				list2.add(vo);
			}
		}
		return list2;
	}
	
	public List<GuideReportVO> getFinish(){
		List<GuideReportVO> list = dao.getAll();
		List<GuideReportVO> list2 = new ArrayList<>();
		for(GuideReportVO vo : list) {
			if(!"GR1".equals(vo.getGuideRep_status())){
				list2.add(vo);
			}
		}
		return list2;
	}
	
	//test
	public static void main(String[] args) {
		GuideReportService grs = new GuideReportService();
		
		//add
//		grs.addGuideReport("GT00002", "test123", "GR1","MB00005");
		
		//update
//		grs.updateGuideReport("GR00004", "GT00002", "test321654", "GR2","MB00005");
		
		//getOne
//		GuideReportVO grVO3 = grs.getOneGuideReport("GR00001");
//		System.out.print(grVO3.getGuideRep_no() + ",");
//		System.out.print(grVO3.getGuideTour_no() + ",");
//		System.out.print(grVO3.getGuideRep_reason() + ",");
//		System.out.print(grVO3.getMem_no() + ",");
//		System.out.print(grVO3.getGuideRep_status());
		
		//getAll
//		List<GuideReportVO> list = grs.getAll();
//		for (GuideReportVO grVO4 : list) {
//			System.out.print(grVO4.getGuideRep_no() + ",");
//			System.out.print(grVO4.getGuideTour_no() + ",");
//			System.out.print(grVO4.getGuideRep_reason() + ",");
//		    System.out.print(grVO4.getMem_no() + ",");
//			System.out.print(grVO4.getGuideRep_status());
//			System.out.println();
//		}
		
	}
}
