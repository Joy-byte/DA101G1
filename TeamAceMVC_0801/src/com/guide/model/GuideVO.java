package com.guide.model;

public class GuideVO implements java.io.Serializable{
	
	private String guide_no;
	private String mem_no;
	private String guide_intro;
	private byte[] guide_pic;
	private byte[] guide_cover;
	private String guide_hobby;
	private String guide_status;
	private Double guide_lng;
	private Double guide_lat;
	private String guide_area;
	private Double guide_rate;
	private String guide_stLang;
	private String guide_ndLang;
	
	public String getGuide_no() {
		return guide_no;
	}
	public void setGuide_no(String guide_no) {
		this.guide_no = guide_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getGuide_intro() {
		return guide_intro;
	}
	public void setGuide_intro(String guide_intro) {
		this.guide_intro = guide_intro;
	}
	public byte[] getGuide_pic() {
		return guide_pic;
	}
	public void setGuide_pic(byte[] guide_pic) {
		this.guide_pic = guide_pic;
	}
	public byte[] getGuide_cover() {
		return guide_cover;
	}
	public void setGuide_cover(byte[] guide_cover) {
		this.guide_cover = guide_cover;
	}
	public String getGuide_hobby() {
		return guide_hobby;
	}
	public void setGuide_hobby(String guide_hobby) {
		this.guide_hobby = guide_hobby;
	}
	public String getGuide_status() {
		return guide_status;
	}
	public void setGuide_status(String guide_status) {
		this.guide_status = guide_status;
	}
	public Double getGuide_lng() {
		return guide_lng;
	}
	public void setGuide_lng(Double guide_lng) {
		this.guide_lng = guide_lng;
	}
	public Double getGuide_lat() {
		return guide_lat;
	}
	public void setGuide_lat(Double guide_lat) {
		this.guide_lat = guide_lat;
	}
	public String getGuide_area() {
		return guide_area;
	}
	public void setGuide_area(String guide_area) {
		this.guide_area = guide_area;
	}
	public Double getGuide_rate() {
		return guide_rate;
	}
	public void setGuide_rate(Double guide_rate) {
		this.guide_rate = guide_rate;
	}
	public String getGuide_stLang() {
		return guide_stLang;
	}
	public void setGuide_stLang(String guide_stLang) {
		this.guide_stLang = guide_stLang;
	}
	public String getGuide_ndLang() {
		return guide_ndLang;
	}
	public void setGuide_ndLang(String guide_ndLang) {
		this.guide_ndLang = guide_ndLang;
	}
}