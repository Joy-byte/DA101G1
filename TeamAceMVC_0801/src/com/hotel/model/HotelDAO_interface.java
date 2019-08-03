package com.hotel.model;

import java.util.List;

public interface HotelDAO_interface {

    public List<HotelVO> getAll();
    public HotelVO findByLngLat(Double hotel_lng, Double hotel_lat);
    public HotelVO findByHotelName(String Hotel_name);
}
