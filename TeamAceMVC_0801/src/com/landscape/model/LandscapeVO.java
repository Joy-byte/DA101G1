package com.landscape.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LandscapeVO implements java.io.Serializable{
	private String landscape_no;
	private Double landscape_lng;
	private Double landscape_lat;
	private String landscape_name;
	private String landscape_status;
	private String landscape_add;
	
	private String landscape_city;

	public String getLandscape_no() {
		return landscape_no;
	}
	public void setLandscape_no(String landscape_no) {
		this.landscape_no = landscape_no;
	}
	public Double getLandscape_lng() {
		return landscape_lng;
	}
	public void setLandscape_lng(Double landscape_lng) {
		this.landscape_lng = landscape_lng;
	}
	public Double getLandscape_lat() {
		return landscape_lat;
	}
	public void setLandscape_lat(Double landscape_lat) {
		this.landscape_lat = landscape_lat;
	}
	public String getLandscape_name() {
		return landscape_name;
	}
	public void setLandscape_name(String landscape_name) {
		this.landscape_name = landscape_name;
	}
	public String getLandscape_status() {
		return landscape_status;
	}
	public void setLandscape_status(String landscape_status) {
		this.landscape_status = landscape_status;
	}
	public String getLandscape_add() {
		return landscape_add;
	}
	
	public void setLandscape_add(String landscape_add) {
		this.landscape_add = landscape_add;
	}			

	public String getLandscape_city() { 
		return landscape_city;
	}
	public void setLandscape_city(String landscape_city) {
		   this.landscape_city =landscape_city ;
  
	}
	
	

}
