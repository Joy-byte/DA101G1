package com.hotel.model;

import java.util.List;




public class HotelService {

	private HotelDAO_interface dao;
	public HotelService() {
		dao = new HotelDAO();
	}

	
	public HotelVO getOneHotel(Double hotel_lng, Double hotel_lat) {
		return dao.findByLngLat(hotel_lng, hotel_lat);
	}
	
	public HotelVO getOneHotel(String hotel_name) {
		return dao.findByHotelName(hotel_name);
	}
	
	public List<HotelVO> getAll() {
		return dao.getAll();
	}
}
