package com.order_list.model;

import java.util.List;

public interface Order_listDAO_interface {
	public void insert(Order_listVO order_listVO);

	public void update(Order_listVO order_listVO);

	public void delete(String order_no, String product_no);

	public Order_listVO findByPrimaryKey(String order_no, String product_no);

	public List<Order_listVO> getAll();

	public void insertWithOrderDetail(Order_listVO order_listVO, java.sql.Connection con);
    
	public List<Order_listVO> findByOrder_no(String order_no);
	
	
	//查詢單一訂單有關明細
  	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no);
  	
  	//查詢單一訂單有關明細有限單一廠商
  	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no,String merchant_no);
  	
  	 // 管理員查詢一個月單一廠商已出貨全部訂單總金額
   	public Order_listVO getOneMonthOfMerchant(String month,String merchant_no);
}
