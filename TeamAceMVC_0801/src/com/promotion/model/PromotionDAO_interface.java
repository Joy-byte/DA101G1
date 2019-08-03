package com.promotion.model;

import java.util.*;

import com.merchant.model.MerchantVO;

public interface PromotionDAO_interface {
 	public void insert(PromotionVO promotionVO);
 	public void update(PromotionVO promotionVO);
 	public void delete(String productreport_no);
 	public PromotionVO findByPrimaryKey(String productreport_no);
 	public List<PromotionVO> getAll();
 //萬用複合查詢(傳入參數型態Map)(回傳 List)
//public List<PromotionVO> getAll(Map<String, String[]> map); 
 	
 	// 查詢單一廠商有關廣告
 	public List<PromotionVO> findByMerchantNo(String merchant_no); 
 	
 	// 更改用單一廣告編號更改上下架狀態並PromotionVO傳回
 	public void updatePromotionStatus(PromotionVO promotionVO);
 	
 	// 查詢單一廣告狀態用狀態並PromotionVO傳回list
  	public List<PromotionVO> getOneStatusOfAll(String promotion_status);
  	
  	//用商品編號找出廣告VO(廣告折扣用)
  	public PromotionVO findByProductNo(String product_no);
  	
  	
}
