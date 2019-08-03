package com.itinerary_report.model;

import java.util.List;

public interface Itinerary_reportDAO_interface {
	  public void insert(Itinerary_reportVO itinerary_reportVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<Itinerary_reportVO> getAll();
}
