package com.itineraryStart.model;

import java.util.List;

import com.landscape.model.LandscapeVO;




public class ItineraryStartService {

	private ItineraryStartDAO_interface dao;
	
	public ItineraryStartService() {
		dao = new ItineraryStartDAO();
	}
	
	
	public ItineraryStartVO addIti( String itinerary_no, String mem_no) {

		ItineraryStartVO itineraryStartVO = new ItineraryStartVO();
		itineraryStartVO.setItinerary_no(itinerary_no);
		itineraryStartVO.setMem_no(mem_no);
//		itineraryStartVO.setItinerary_status("");
		dao.insert(itineraryStartVO);

		return itineraryStartVO;
	}
	
	public void addIti(ItineraryStartVO itineraryStartVO) {
		dao.insert(itineraryStartVO);
	}
	

	public List<ItineraryStartVO> getAll() {
		return dao.getAll();
	}

	
	public List<ItineraryStartVO> getItineraryStartByMemNo(String mem_no) {
		return dao.getByMemNo(mem_no);
	}
}
