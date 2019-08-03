package com.gtclist.model;

import java.util.List;

public class GTCListService {
	private GTCListDAO_interface dao;
	
	public GTCListService() {
		dao = new GTCListDAO();
	}
	
	public GTCListVO addGTCList(String guideTour_no,String category) {
		GTCListVO gtcListVO = new GTCListVO();
		gtcListVO.setGuideTour_no(guideTour_no);
		gtcListVO.setCategory(category);
		dao.insert(gtcListVO);
		
		return gtcListVO;
	}
	
	public void deleteGTCList(String guideTour_no,String category) {
		dao.delete(guideTour_no, category);
	}
	
	public List<GTCListVO> getCategoryByGTno(String guideTour_no) {
		return dao.getCategoryByGTno(guideTour_no);
	}
	
	public List<GTCListVO> getGuideTourByCno(String category){
		return dao.getGuideTourByCno(category);
	}
	
	public List<GTCListVO> getAll(){
		return dao.getAll();
	}
	
	//test
	public static void main(String[] args) {
		GTCListService gtcls = new GTCListService();
		
		//add
//		gtcls.addGTCList("GT00002", "GC00002");
		
		//update
//		gtcls.deleteGTCList("GT00002", "GC00002");
		
		//getCategoryByGTno
//		List<GTCListVO> list = gtcls.getCategoryByGTno("GT00001");
//		for(GTCListVO vo : list) {
//			System.out.print(vo.getGuideTour_no()+",");
//			System.out.print(vo.getCategory());
//			System.out.println();
//		}
		
		//getGuideTourByCno
//		List<GTCListVO> list2 = gtcls.getGuideTourByCno("GC00001");
//		for(GTCListVO vo2 : list2) {
//			System.out.print(vo2.getGuideTour_no()+",");
//			System.out.print(vo2.getCategory());
//			System.out.println();
//		}
		
		//getAll
//		List<GTCListVO> list3 = gtcls.getAll();
//		for(GTCListVO vo3 : list3) {
//			System.out.print(vo3.getGuideTour_no()+",");
//			System.out.print(vo3.getCategory());
//			System.out.println();
//		}
	}
}
