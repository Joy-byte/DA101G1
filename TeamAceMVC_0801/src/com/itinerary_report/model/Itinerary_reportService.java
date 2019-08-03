package com.itinerary_report.model;

import java.util.List;





public class Itinerary_reportService {

	private Itinerary_reportDAO_interface dao;
	public Itinerary_reportService() {
		dao = new Itinerary_reportDAO();
	}

	public Itinerary_reportVO addGad(String itinerary_no,
			String irep_detail, String irep_status) {

		Itinerary_reportVO itinerary_reportVO = new Itinerary_reportVO();
		itinerary_reportVO.setItinerary_no(itinerary_no);
		itinerary_reportVO.setIrep_detail(irep_detail);
		itinerary_reportVO.setIrep_status(irep_status);
		dao.insert(itinerary_reportVO);

		return itinerary_reportVO;
	}
	
	

	public List<Itinerary_reportVO> getAll() {
		return dao.getAll();
	}
}
