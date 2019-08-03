package com.gtcategory.model;

import java.util.List;

public class GTCategoryService {
	private GTCategoryDAO_interface dao;
	
	public GTCategoryService() {
		dao = new GTCategoryDAO();
	}
	
	public GTCategoryVO addGTCategory(String category_name) {
		
		GTCategoryVO gtcVO = new GTCategoryVO();
		
		gtcVO.setCategory_name(category_name);
		dao.insert(gtcVO);
		
		return gtcVO;
	}
	
	public GTCategoryVO updateGTCategory(String category_no,String category_name) {
		
		GTCategoryVO gtcVO2 = new GTCategoryVO();
		
		gtcVO2.setCategory_no(category_no);
		gtcVO2.setCategory_name(category_name);
		dao.update(gtcVO2);
		
		return gtcVO2;
	}
	
	public void deleteGTCategory(String category_no) {
		dao.delete(category_no);
	}
	
	public GTCategoryVO getOneGTCategory(String category_no) {
		return dao.findByPrimaryKey(category_no);
	}
	
	public List<GTCategoryVO> getAll(){
		return dao.getAll();
	}
	
	//test
	public static void main(String[] args) {
		GTCategoryService gtcs = new GTCategoryService();
		
		//add
//		gtcs.addGTCategory("美食");
//		gtcs.addGTCategory("美食2");
		
		//update
//		gtcs.updateGTCategory("GC00006", "娛樂");
		
		//delete
//		gtcs.deleteGTCategory("GC00006");
		
		//getOne
//		GTCategoryVO vo = gtcs.getOneGTCategory("GC00001");
//		System.out.print(vo.getCategory_no()+",");
//		System.out.print(vo.getCategory_name());
		
		//getAll
//		List<GTCategoryVO> list = gtcs.getAll();
//		for(GTCategoryVO vo2 : list) {
//			System.out.print(vo2.getCategory_no()+",");
//			System.out.print(vo2.getCategory_name());
//			System.out.println();
//		}
	}
}
