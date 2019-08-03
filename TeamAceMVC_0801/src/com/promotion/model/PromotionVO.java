package com.promotion.model;

import java.sql.Date;

public class PromotionVO implements java.io.Serializable {
	private String promotion_no;
	private String merchant_no;
	private String product_no;
	private String promotion_name;
	private Date   promotion_start;
	private Date   promotion_end;
	private Double promotion_pr;
	private String promotion_ps;
	private String promotion_status;
	private byte[] promotion_img;
	
	public String getPromotion_no() {
		return promotion_no;
	}
	public void setPromotion_no(String promotion_no) {
		this.promotion_no = promotion_no;
	}
	public String getMerchant_no() {
		return merchant_no;
	}
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getPromotion_name() {
		return promotion_name;
	}
	public void setPromotion_name(String promotion_name) {
		this.promotion_name = promotion_name;
	}
	public Date getPromotion_start() {
		return promotion_start;
	}
	public void setPromotion_start(Date promotion_start) {
		this.promotion_start = promotion_start;
	}
	public Date getPromotion_end() {
		return promotion_end;
	}
	public void setPromotion_end(Date promotion_end) {
		this.promotion_end = promotion_end;
	}
	public Double getPromotion_pr() {
		return promotion_pr;
	}
	public void setPromotion_pr(Double promotion_pr) {
		this.promotion_pr = promotion_pr;
	}
	public String getPromotion_ps() {
		return promotion_ps;
	}
	public void setPromotion_ps(String promotion_ps) {
		this.promotion_ps = promotion_ps;
	}
	public String getPromotion_status() {
		return promotion_status;
	}
	public void setPromotion_status(String promotion_status) {
		this.promotion_status = promotion_status;
	}
	public byte[] getPromotion_img() {
		return promotion_img;
	}
	public void setPromotion_img(byte[] promotion_img) {
		this.promotion_img = promotion_img;
	}
	
	
	

}
