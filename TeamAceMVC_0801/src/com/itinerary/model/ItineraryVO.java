package com.itinerary.model;

import java.sql.Date;

public class ItineraryVO implements java.io.Serializable {
	private String itinerary_no;
	private String mem_no;
	private String itinerary_status;
	private Date itinerary_start;
	private Date itinerary_end;
	
	
	private byte[] itinerary_pic;
	
	public byte[] getItinerary_pic() {
		return itinerary_pic;
	}
	public void setItinerary_pic(byte[] itinerary_pic) {
		this.itinerary_pic = itinerary_pic;
	}
	public String getItinerary_no() {
		return itinerary_no;
	}
	public void setItinerary_no(String itinerary_no) {
		this.itinerary_no = itinerary_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getItinerary_status() {
		return itinerary_status;
	}
	public void setItinerary_status(String itinerary_status) {
		this.itinerary_status = itinerary_status;
	}
	public Date getItinerary_start() {
		return itinerary_start;
	}
	public void setItinerary_start(Date itinerary_start) {
		this.itinerary_start = itinerary_start;
	}
	public Date getItinerary_end() {
		return itinerary_end;
	}
	public void setItinerary_end(Date itinerary_end) {
		this.itinerary_end = itinerary_end;
	}
	

	
		
	
	
	
	
	
}
