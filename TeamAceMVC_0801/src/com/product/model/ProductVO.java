package com.product.model;

public class ProductVO implements java.io.Serializable {
	private String product_no;
	private String merchant_no;
	private String product_name;
	private String product_status;
	private Integer product_pr;
	private String product_typ;
	private String product_pn;
	private String product_ps;
	private byte[] product_img;
	private Integer product_quan;
	
	
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getMerchant_no() {
		return merchant_no;
	}
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public Integer getProduct_pr() {
		return product_pr;
	}
	public void setProduct_pr(Integer product_pr) {
		this.product_pr = product_pr;
	}
	public String getProduct_typ() {
		return product_typ;
	}
	public void setProduct_typ(String product_typ) {
		this.product_typ = product_typ;
	}
	public String getProduct_pn() {
		return product_pn;
	}
	public void setProduct_pn(String product_pn) {
		this.product_pn = product_pn;
	}
	public String getProduct_ps() {
		return product_ps;
	}
	public void setProduct_ps(String product_ps) {
		this.product_ps = product_ps;
	}
	public byte[] getProduct_img() {
		return product_img;
	}
	public void setProduct_img(byte[] product_img) {
		this.product_img = product_img;
	}
	public Integer getProduct_quan() {
		return product_quan;
	}
	public void setProduct_quan(Integer product_quan) {
		this.product_quan = product_quan;
	}

	
	


}
