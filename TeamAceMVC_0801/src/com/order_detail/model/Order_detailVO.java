package com.order_detail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order_detailVO implements Serializable {
	
	String order_no;
	String mem_no;
	String merchant_no;
	String order_status;
	Integer order_amosum;
	Timestamp order_time;
	String order_cusadr;
	String order_cusname;
	String order_cusphone;
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getMerchant_no() {
		return merchant_no;
	}
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public Integer getOrder_amosum() {
		return order_amosum;
	}
	public void setOrder_amosum(Integer order_amosum) {
		this.order_amosum = order_amosum;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public String getOrder_cusadr() {
		return order_cusadr;
	}
	public void setOrder_cusadr(String order_cusadr) {
		this.order_cusadr = order_cusadr;
	}
	public String getOrder_cusname() {
		return order_cusname;
	}
	public void setOrder_cusname(String order_cusname) {
		this.order_cusname = order_cusname;
	}
	public String getOrder_cusphone() {
		return order_cusphone;
	}
	public void setOrder_cusphone(String order_cusphone) {
		this.order_cusphone = order_cusphone;
	}
	
	
	
	

}
