package com.order_list.model;

import java.util.List;

public class Order_listService {
	private Order_listDAO_interface dao;

	public Order_listService() {
		dao = new Order_listDAO();
	}

	public Order_listVO addOrder_list(String order_no, String product_no, Integer order_product_pr,
			Integer order_quan) {

		Order_listVO order_listVO = new Order_listVO();

		order_listVO.setOrder_no(order_no);
		order_listVO.setProduct_no(product_no);
		order_listVO.setOrder_product_pr(order_product_pr);
		order_listVO.setOrder_quan(order_quan);

		dao.insert(order_listVO);

		return order_listVO;
	}

	public Order_listVO updateOrder_list(String order_no, String product_no, Integer order_product_pr,
			Integer order_quan) {

		Order_listVO order_listVO = new Order_listVO();

		order_listVO.setOrder_no(order_no);
		order_listVO.setProduct_no(product_no);
		order_listVO.setOrder_product_pr(order_product_pr);
		order_listVO.setOrder_quan(order_quan);
		dao.update(order_listVO);

		return order_listVO;
	}

	public void deleteOrder_list(String order_no, String product_no) {
		dao.delete(order_no, product_no);
	}

	public Order_listVO getOneOrder_list(String order_no, String product_no) {
		return dao.findByPrimaryKey(order_no, product_no);
	}
	
	public List<Order_listVO> getMyOrder_list(String order_no) {
		return dao.findByOrder_no(order_no);
	}

	public List<Order_listVO> getAll() {
		return dao.getAll();
	}
	
	
	//查詢單一訂單明細
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no) {
		return dao.findOneOrder_listByOrder_no(order_no);
	}
		
		
	//查詢單一訂單明細限單一廠商
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no,String merchant_no) {
		return dao.findOneOrder_listByOrder_no(order_no,merchant_no);
	}
	
	// 管理員查詢一個月單一廠商已出貨全部訂單總金額
		public Order_listVO getOneMonthOfMerchant (String month,String merchant_no) {
			return dao.getOneMonthOfMerchant( month, merchant_no);
		}
}
