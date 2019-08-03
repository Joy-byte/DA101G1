package com.itinerary.model;

import java.util.List;

import com.landscape.model.LandscapeVO;

public interface ItineraryDAO_interface {
	  public String insert(ItineraryVO itineraryVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<ItineraryVO> getAll();
      public List<ItineraryVO> getLastFour();
      public List<ItineraryVO> getByMemNo(String mem_no);
      public ItineraryVO findByPrimaryKey(String itinerary_no);
}
