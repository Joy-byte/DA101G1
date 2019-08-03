package com.order_detail.model;

import java.util.List;
import java.util.Map;

import com.order_list.model.Order_listVO;



public interface Order_detailDAO_interface {
    public void insert(Order_detailVO order_detailVO);
    public void update(Order_detailVO order_detailVO);
    public void delete(String order_no);
    public Order_detailVO findByPrimaryKey(String order_no);
    public List<Order_detailVO> getAll();
    public void insertWithOrder_list(Order_detailVO order_detailVO, List<Order_listVO> list);
	public List<Order_detailVO> getByMemNo(String mem_no);
	
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<Order_detailVO> getAll(Map<String, String[]> map); 
    
    
 // 查詢單一廠商有關訂單
  	public List<Order_detailVO> findOrder_detailByMerchantNo(String merchant_no); 
  	
 // 前台用單一訂單編號更改出貨狀態並Order_detailVO傳回
  	public void updateOrder_detailStatus(Order_detailVO order_detailVO);
  	
 	// 單一廠商查詢全部訂單單一狀態並Order_detailVO傳回list
  	public List<Order_detailVO> getOneStatusOrder_detailByMerchantNo(String order_status,String merchant_no);
  	
 	// 管理員查詢一個月單一廠商已出貨全部訂單
  	public List<Order_detailVO> getOneMonthOfOneMerchantNo (String month,String merchant_no);
  	
 // 管理員查詢一個月單一廠商已出貨全部訂單總金額
  	public Order_detailVO getOneMonthOfMerchant(String month,String merchant_no);
  	
  	
	//前台廠商輸入時間區間找當日訂單
  	public List<Order_detailVO> getAllOneDayOfMerchantNo(String order_time_start,String order_time_end,String merchant_no);
  	
  	
  	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<Order_detailVO> getAll(Map<String, String[]> map,String merchant_no); 
}
