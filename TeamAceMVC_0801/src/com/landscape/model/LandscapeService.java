package com.landscape.model;

import java.util.List;
import java.util.Set;





public class LandscapeService {

	private LandscapeDAO_interface dao;
	public LandscapeService() {
		dao = new LandscapeDAO();
	}

	public LandscapeVO addLas( Double landscape_lng, Double landscape_lat,
			String landscape_name, String landscape_status, String landscape_add) {

		LandscapeVO landscapeVO = new LandscapeVO();
		landscapeVO.setLandscape_lng(landscape_lng);
		landscapeVO.setLandscape_lat(landscape_lat);
		landscapeVO.setLandscape_name(landscape_name);
		landscapeVO.setLandscape_status(landscape_status);
		landscapeVO.setLandscape_add(landscape_add);
		dao.insert(landscapeVO);

		return landscapeVO;
	}
	
	
	public LandscapeVO updateLas(String landscape_no, Double landscape_lng, Double landscape_lat,
			String landscape_name, String landscape_status, String landscape_add) {
		LandscapeVO landscapeVO = new LandscapeVO();
		landscapeVO.setLandscape_no(landscape_no);
		landscapeVO.setLandscape_lng(landscape_lng);
		landscapeVO.setLandscape_lat(landscape_lat);
		landscapeVO.setLandscape_name(landscape_name);
		landscapeVO.setLandscape_status(landscape_status);
		landscapeVO.setLandscape_add(landscape_add);
		dao.update(landscapeVO);
		return landscapeVO;
	}
	

	public void deleteLas(String landscape_no) {
		dao.delete(landscape_no);
	}
	
	
	public LandscapeVO getOneLas(String landscape_no) {
		return dao.findByPrimaryKey(landscape_no);
	}
	
	public List<LandscapeVO> getAll() {
		return dao.getAll();
	}
	public Set<String> getAllCity() {
		return dao.getAllCity();
	}
}
