package com.guidetour.model;

public class GuideTourVO implements java.io.Serializable{
	
	private String guideTour_no;
	private String guide_no;
	private String guideTour_area;
	private String guideTour_detail;
	private String guideTour_name;
	private Integer guideTour_peo;
	private byte[] guideTour_pic;
	private String guideTour_status;
	private Double guideTour_rate;
	
	public String getGuideTour_no() {
		return guideTour_no;
	}
	public void setGuideTour_no(String guideTour_no) {
		this.guideTour_no = guideTour_no;
	}
	public String getGuide_no() {
		return guide_no;
	}
	public void setGuide_no(String guide_no) {
		this.guide_no = guide_no;
	}
	public String getGuideTour_area() {
		return guideTour_area;
	}
	public void setGuideTour_area(String guideTour_area) {
		this.guideTour_area = guideTour_area;
	}
	public String getGuideTour_detail() {
		return guideTour_detail;
	}
	public void setGuideTour_detail(String guideTour_detail) {
		this.guideTour_detail = guideTour_detail;
	}
	public String getGuideTour_name() {
		return guideTour_name;
	}
	public void setGuideTour_name(String guideTour_name) {
		this.guideTour_name = guideTour_name;
	}
	public Integer getGuideTour_peo() {
		return guideTour_peo;
	}
	public void setGuideTour_peo(Integer guideTour_peo) {
		this.guideTour_peo = guideTour_peo;
	}
	public byte[] getGuideTour_pic() {
		return guideTour_pic;
	}
	public void setGuideTour_pic(byte[] guideTour_pic) {
		this.guideTour_pic = guideTour_pic;
	}
	public String getGuideTour_status() {
		return guideTour_status;
	}
	public void setGuideTour_status(String guideTour_status) {
		this.guideTour_status = guideTour_status;
	}
	public Double getGuideTour_rate() {
		return guideTour_rate;
	}
	public void setGuideTour_rate(Double guideTour_rate) {
		this.guideTour_rate = guideTour_rate;
	}
}
