package com.merchant.model;

import java.util.*;

import com.promotion.model.PromotionVO;

public interface MerchantDAO_interface {
    public void insert(MerchantVO merchantVO);
    public void update(MerchantVO merchantVO);
    public void delete(String merchant_no);
    public MerchantVO findByPrimaryKey(String merchant_no);
    public List<MerchantVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
    //輸入帳號登入用,傳回將session加入廠商編號
    public MerchantVO findByMerchantId(String merchant_id);
    
    
    // 查詢單一廠商用編號並MerchantVO傳回list
 	public List<MerchantVO> getAllOneMerchantVO(String merchant_no); 
 	
    // 更改單一廠商用編號更改狀態並MerchantVO傳回
 	public void updateMerchantStatus(MerchantVO merchantVO);
 	
    // 查詢單一廠商狀態用狀態並MerchantVO傳回list
 	public List<MerchantVO> getOneStatusOfAll(String merchant_status);
    
}
