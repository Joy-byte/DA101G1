package com.resorder.model;
import java.sql.Date;

public class ResOrderVO implements java.io.Serializable{
	private String resOrder_no;
	private String mem_no;
	private String guide_no;
	private String guideTour_no;
	private Date resOrder_date;
	private Integer resOrder_peo;
	private String resOrder_msg;
	private String resOrder_status;
	private String resOrder_joinpeo;
	private Double resOrder_rate;
	private String resOrder_cancel;
	private String resOrder_review;
	private Date resOrder_time;
	
	public String getResOrder_no() {
		return resOrder_no;
	}
	public void setResOrder_no(String resOrder_no) {
		this.resOrder_no = resOrder_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String Mem_no) {
		this.mem_no = Mem_no;
	}
	public String getGuide_no() {
		return guide_no;
	}
	public void setGuide_no(String guide_no) {
		this.guide_no = guide_no;
	}
	public String getGuideTour_no() {
		return guideTour_no;
	}
	public void setGuideTour_no(String guideTour_no) {
		this.guideTour_no = guideTour_no;
	}
	public Date getResOrder_date() {
		return resOrder_date;
	}
	public void setResOrder_date(Date resOrder_date) {
		this.resOrder_date = resOrder_date;
	}
	public Integer getResOrder_peo() {
		return resOrder_peo;
	}
	public void setResOrder_peo(Integer resOrder_peo) {
		this.resOrder_peo = resOrder_peo;
	}
	public String getResOrder_msg() {
		return resOrder_msg;
	}
	public void setResOrder_msg(String resOrder_msg) {
		this.resOrder_msg = resOrder_msg;
	}
	public String getResOrder_status() {
		return resOrder_status;
	}
	public void setResOrder_status(String resOrder_status) {
		this.resOrder_status = resOrder_status;
	}
	public String getResOrder_joinpeo() {
		return resOrder_joinpeo;
	}
	public void setResOrder_joinpeo(String resOrder_joinpeo) {
		this.resOrder_joinpeo = resOrder_joinpeo;
	}
	public Double getResOrder_rate() {
		return resOrder_rate;
	}
	public void setResOrder_rate(Double resOrder_rate) {
		this.resOrder_rate = resOrder_rate;
	}
	public String getResOrder_cancel() {
		return resOrder_cancel;
	}
	public void setResOrder_cancel(String resOrder_cancel) {
		this.resOrder_cancel = resOrder_cancel;
	}
	public String getResOrder_review() {
		return resOrder_review;
	}
	public void setResOrder_review(String resOrder_review) {
		this.resOrder_review = resOrder_review;
	}
	public Date getResOrder_time() {
		return resOrder_time;
	}
	public void setResOrder_time(Date resOrder_time) {
		this.resOrder_time = resOrder_time;
	}
	
}
