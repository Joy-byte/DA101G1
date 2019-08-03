package com.guidereport.model;

public class GuideReportVO implements java.io.Serializable{
	private String guideRep_no;
	private String guideTour_no;
	private String guideRep_reason;
	private String guideRep_status;
	private String mem_no;
	
	public String getGuideRep_no() {
		return guideRep_no;
	}
	public void setGuideRep_no(String guideRep_no) {
		this.guideRep_no = guideRep_no;
	}
	public String getGuideRep_reason() {
		return guideRep_reason;
	}
	public void setGuideRep_reason(String guideRep_reason) {
		this.guideRep_reason = guideRep_reason;
	}
	public String getGuideRep_status() {
		return guideRep_status;
	}
	public void setGuideRep_status(String guideRep_status) {
		this.guideRep_status = guideRep_status;
	}
	public String getGuideTour_no() {
		return guideTour_no;
	}
	public void setGuideTour_no(String guideTour_no) {
		this.guideTour_no = guideTour_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
}
