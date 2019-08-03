package com.hotel.model;

import java.io.Serializable;

public class HotelVO implements Serializable {
	private String hotel_no;
	private Double hotel_lng;
	private Double hotel_lat;
	private String hotel_name;
	private String hotel_add;
	
	public String getHotel_no() {
		return hotel_no;
	}
	public void setHotel_no(String hotel_no) {
		this.hotel_no = hotel_no;
	}
	public Double getHotel_lng() {
		return hotel_lng;
	}
	public void setHotel_lng(Double hotel_lng) {
		this.hotel_lng = hotel_lng;
	}
	public Double getHotel_lat() {
		return hotel_lat;
	}
	public void setHotel_lat(Double hotel_lat) {
		this.hotel_lat = hotel_lat;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getHotel_add() {
		return hotel_add;
	}
	public void setHotel_add(String hotel_add) {
		this.hotel_add = hotel_add;
	}

}
