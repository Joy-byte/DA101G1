package com.itinerary_track.model;

import java.util.List;

public class Itinerary_trackService {

	private Itinerary_trackDAO_interface dao;

	public Itinerary_trackService() {
		dao = new Itinerary_trackDAO();
	}

	public Itinerary_trackVO addIrp(String itinerary_no, String mem_no) {
		Itinerary_trackVO itinerary_reportVO = new Itinerary_trackVO();
		itinerary_reportVO.setItinerary_no(itinerary_no);
		itinerary_reportVO.setMem_no(mem_no);
		dao.insert(itinerary_reportVO);

		return itinerary_reportVO;
	}

	public List<Itinerary_trackVO> getMemberItinerary_track(String itinerary_no, String mem_no) {
		return dao.findMemberItinerary_track(itinerary_no, mem_no);
	}
	public List<Itinerary_trackVO> getMemberItinerary_track(String mem_no) {
		return dao.findItinerary_trackFormem(mem_no);
	}

	public List<Itinerary_trackVO> getAll() {
		return dao.getAll();
	}

	public Itinerary_trackVO addItinerary_track(String itinerary_no, String mem_no) {
		Itinerary_trackVO itinerary_trackVO = new Itinerary_trackVO();
		itinerary_trackVO.setItinerary_no(itinerary_no);
		itinerary_trackVO.setMem_no(mem_no);
		dao.insert(itinerary_trackVO);

		return itinerary_trackVO;

	}

	public void deleteItinerary_track(String mem_no, String itinerary_no) {
		dao.delete(mem_no, itinerary_no);	
	}
}
