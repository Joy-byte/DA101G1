package com.product.model;
import java.util.*;


	public interface ProductDAO_interface {
        public void insert(ProductVO productVO);
        public void update(ProductVO productVO);
        public void delete(String product_no);
        public ProductVO findByPrimaryKey(String product_no);
        public List<ProductVO> getAll();
        // 查詢單一廠商有關商品
     	public List<ProductVO> findByMerchantNo(String merchant_no);
     	
     // 更改用單一商品編號更改上下架狀態並ProductVO傳回
     	public void updateProductStatus(ProductVO productVO);
     	
     // 查詢單一商品狀態用狀態並PromotionVO傳回list
      	public List<ProductVO> getOneStatusOfAll(String product_status);
      	
      //查詢上下架狀態和廣告狀態傳回商品list
      	public List<ProductVO> getOneStatusOfAll(String product_status,String product_pn);
      	
        // 更改用單一商品編號更改廣告上下架狀態並ProductVO傳回
     	public void updateProductOfPN(ProductVO productVO);
	}

