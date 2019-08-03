package com.order_list.model;

import java.io.Serializable;

public class Order_listVO implements Serializable {
	
	String order_no;
    String product_no;
    Integer order_product_pr;
    Integer order_quan;
	
	 public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public Integer getOrder_product_pr() {
		return order_product_pr;
	}
	public void setOrder_product_pr(Integer order_product_pr) {
		this.order_product_pr = order_product_pr;
	}
	public Integer getOrder_quan() {
		return order_quan;
	}
	public void setOrder_quan(Integer order_quan) {
		this.order_quan = order_quan;
	}
	
}
