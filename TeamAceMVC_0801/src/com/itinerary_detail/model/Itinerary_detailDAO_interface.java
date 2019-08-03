package com.itinerary_detail.model;

import java.util.List;

import com.itinerary.model.ItineraryVO;

public interface Itinerary_detailDAO_interface {
	    public void insert(Itinerary_detailVO itinerary_detailVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<Itinerary_detailVO> getAll();
      public List<Itinerary_detailVO> getLas(String itinerary_no);
      public Itinerary_detailVO findByPrimaryKey(String itinerary_no);
}
