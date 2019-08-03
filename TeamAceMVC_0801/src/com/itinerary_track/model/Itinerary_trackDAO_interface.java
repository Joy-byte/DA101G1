package com.itinerary_track.model;

import java.util.List;

public interface Itinerary_trackDAO_interface {
	public void insert(Itinerary_trackVO itinerary_trackVO);

//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
	public List<Itinerary_trackVO> getAll();

	public List<Itinerary_trackVO> findMemberItinerary_track(String itinerary_no, String mem_no);

	public List<Itinerary_trackVO> findItinerary_trackFormem(String mem_no);

	public void delete(String mem_no, String itinerary_no);
}
