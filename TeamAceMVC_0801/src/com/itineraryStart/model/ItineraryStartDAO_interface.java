package com.itineraryStart.model;

import java.util.List;

public interface ItineraryStartDAO_interface {
	  public void insert(ItineraryStartVO itineraryStartVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<ItineraryStartVO> getAll();
      public List<ItineraryStartVO> getByMemNo(String mem_no);
}
