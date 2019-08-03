package com.condition.model;

import java.util.List;





public class ConditionService {

	private ConditionDAO_interface dao;
	public ConditionService() {
		dao = new ConditionDAO();
	}

	public ConditionVO addCod( String itinerary_no, String landscape_no,
			String condition_content, Integer condition_day) {

		ConditionVO conditionVO = new ConditionVO();
		conditionVO.setItinerary_no(itinerary_no);
		conditionVO.setLandscape_no(landscape_no);
		conditionVO.setCondition_content(condition_content);
		conditionVO.setCondition_day(condition_day);
		dao.insert(conditionVO);
		return conditionVO;
	}
	

	
	public List<ConditionVO> getAll() {
		return dao.getAll();
	}
}
