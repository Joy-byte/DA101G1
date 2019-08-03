package com.gtlist.model;

public class GTListVO implements java.io.Serializable{
	private String guideTour_no;
	private String landscape_no;
	private byte[] landscape_pic;
	private String landscape_trans;
	private Integer landscape_hr;
	private Integer landscape_min;
	private Integer landscape_order;
	
	public String getGuideTour_no() {
		return guideTour_no;
	}
	public void setGuideTour_no(String guideTour_no) {
		this.guideTour_no = guideTour_no;
	}
	public String getLandscape_no() {
		return landscape_no;
	}
	public void setLandscape_no(String landscape_no) {
		this.landscape_no = landscape_no;
	}
	public byte[] getLandscape_pic() {
		return landscape_pic;
	}
	public void setLandscape_pic(byte[] landscape_pic) {
		this.landscape_pic = landscape_pic;
	}
	public String getLandscape_trans() {
		return landscape_trans;
	}
	public void setLandscape_trans(String landscape_trans) {
		this.landscape_trans = landscape_trans;
	}
	public Integer getLandscape_hr() {
		return landscape_hr;
	}
	public void setLandscape_hr(Integer landscape_hr) {
		this.landscape_hr = landscape_hr;
	}
	public Integer getLandscape_min() {
		return landscape_min;
	}
	public void setLandscape_min(Integer landscape_min) {
		this.landscape_min = landscape_min;
	}
	public Integer getLandscape_order() {
		return landscape_order;
	}
	public void setLandscape_order(Integer landscape_order) {
		this.landscape_order = landscape_order;
	}
	
}
