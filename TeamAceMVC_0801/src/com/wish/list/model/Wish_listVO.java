package com.wish.list.model;

import java.io.Serializable;

public class Wish_listVO implements Serializable {
	  String mem_no;
	  String product_no;
	
	
  public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
}
