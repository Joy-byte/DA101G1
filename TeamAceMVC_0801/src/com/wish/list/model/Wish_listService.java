package com.wish.list.model;

import java.util.List;

public class Wish_listService {
	private Wish_listDAO_interface dao;

	public Wish_listService() {
		dao = new Wish_listDAO();
	}

	public Wish_listVO addWish_list(String mem_no, String product_no) {

		Wish_listVO wish_listVO = new Wish_listVO();
		wish_listVO.setMem_no(mem_no);
		wish_listVO.setProduct_no(product_no);
		dao.insert(wish_listVO);

		return wish_listVO;
	}
	
	public void deleteWish_list(String mem_no, String product_no) {
		dao.delete(mem_no, product_no);
	}

	public List<Wish_listVO> getMemberWish_list(String mem_no) {
		return dao.findByMemberNo(mem_no);
	}
	
	public List<Wish_listVO> getMemberWish_list(String mem_no, String product_no) {
		return dao.findByPrimaryKey(mem_no, product_no);
	}

	public List<Wish_listVO> getAll() {
		return dao.getAll();
	}
}
