package com.gtlist.model;

import java.util.List;

public class GTListService {
	private GTListDAO_interface dao;

	public GTListService() {
		dao = new GTListDAO();
	}

	public GTListVO addGTList(String guideTour_no, String landscape_no, byte[] landscape_pic, String landscape_trans,
			Integer landscape_hr, Integer landscape_min, Integer landscape_order) {
		GTListVO gtListVO = new GTListVO();
		gtListVO.setGuideTour_no(guideTour_no);
		gtListVO.setLandscape_no(landscape_no);
		gtListVO.setLandscape_pic(landscape_pic);
		gtListVO.setLandscape_trans(landscape_trans);
		gtListVO.setLandscape_hr(landscape_hr);
		gtListVO.setLandscape_min(landscape_min);
		gtListVO.setLandscape_order(landscape_order);
		dao.insert(gtListVO);

		return gtListVO;
	}
	
	public GTListVO updateGTList(String guideTour_no, String landscape_no, byte[] landscape_pic, String landscape_trans,
			Integer landscape_hr, Integer landscape_min, Integer landscape_order) {
		GTListVO gtListVO2 = new GTListVO();
		gtListVO2.setGuideTour_no(guideTour_no);
		gtListVO2.setLandscape_no(landscape_no);
		gtListVO2.setLandscape_pic(landscape_pic);
		gtListVO2.setLandscape_trans(landscape_trans);
		gtListVO2.setLandscape_hr(landscape_hr);
		gtListVO2.setLandscape_min(landscape_min);
		gtListVO2.setLandscape_order(landscape_order);
		dao.update(gtListVO2);
		
		return gtListVO2;
	}
	
	public void deleteGTList(String guideTour_no, String landscape_no) {
		dao.delete(guideTour_no, landscape_no);
	}
	
	public GTListVO getOneGTList(String guideTour_no, String landscape_no) {
		return dao.findByPrimaryKey(guideTour_no, landscape_no);
	}

	public List<GTListVO> getGTListByGTNo(String guideTour_no){
		return dao.findByGuideTourNo(guideTour_no);
	}
	
	public List<GTListVO> getAll(){
		return dao.getAll();
	}
	
	//test
	public static void main(String[] args) {
		GTListService gtls = new GTListService();
		
		//add
//		gtls.addGTList("GT00009", "LS00001", null, "步行", 1, 10,1);
		
		//update
//		gtls.updateGTList("GT00009", "LS00001", null, "步行", 1, 50,1);
		
		//delete
//		gtls.deleteGTList("GT00002", "LS00001");
		
		//getOne
//		GTListVO vo = gtls.getOneGTList("GT00001", "LS00002");
//		System.out.print(vo.getGuideTour_no()+",");
//		System.out.print(vo.getLandscape_no()+",");
//		System.out.print(vo.getLandscape_pic()+",");
//		System.out.print(vo.getLandscape_trans()+",");
//		System.out.print(vo.getLandscape_hr()+",");
//		System.out.print(vo.getLandscape_min()+",");
//		System.out.print(vo.getLandscape_order());
//		System.out.println();
		
		//getList(by guideTour_no)
//		List<GTListVO> list = gtls.getGTListByGTNo("GT00001");
//		for(GTListVO vo : list) {
//			System.out.print(vo.getGuideTour_no()+",");
//			System.out.print(vo.getLandscape_no()+",");
//			System.out.print(vo.getLandscape_pic()+",");
//			System.out.print(vo.getLandscape_trans()+",");
//			System.out.print(vo.getLandscape_hr()+",");
//			System.out.print(vo.getLandscape_min()+",");
//			System.out.print(vo.getLandscape_order());
//			System.out.println();
//		}
		
		//getAll
//		List<GTListVO> list2 = gtls.getAll();
//		for(GTListVO vo2 : list2) {
//			System.out.print(vo2.getGuideTour_no()+",");
//			System.out.print(vo2.getLandscape_no()+",");
//			System.out.print(vo2.getLandscape_pic()+",");
//			System.out.print(vo2.getLandscape_trans()+",");
//			System.out.print(vo2.getLandscape_hr()+",");
//			System.out.print(vo2.getLandscape_min()+",");
//			System.out.print(vo2.getLandscape_order());
//			System.out.println();
//		}
		
	}
}
