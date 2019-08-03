package com.picture.model;

import java.util.List;

import com.landscape.model.LandscapeVO;


public class PictureService {

	private PictureDAO_interface dao;
	public PictureService() {
		dao = new PictureDAO();
	}
	
	public PictureVO addPic(String itinerary_no, String mem_no,String[] landscape_no, List<byte[]> picbufs) {
		PictureVO pictureVO = new PictureVO();
		int i = 0;
		for(byte[] bufs:picbufs) {			
			pictureVO.setItinerary_no(itinerary_no);
			pictureVO.setMem_no(mem_no);
			pictureVO.setLandscape_no(landscape_no[i++]);
			pictureVO.setPic(bufs);
			dao.insert(pictureVO);
			if(i>picbufs.size())
				i=0;
		}
		return pictureVO;//目前沒用,先回傳最後一筆
	}
	
	public void addIti(PictureVO pictureVO) {
		dao.insert(pictureVO);
	}
	

	public PictureVO getOnePic(String itinerary_no) {
		return dao.findByPrimaryKey(itinerary_no);
	}
	
	public List<PictureVO> getAll() {
		return dao.getAll();
	}
	
	public PictureVO getTheOnePic(String itinerary_no,String landscape_no) {
		return dao.findByItiAndLs(itinerary_no,landscape_no);
	}
	
	public List<PictureVO> getMemberPic(String mem_no) {
		return dao.findByMemId(mem_no);
	}
	
	
}
