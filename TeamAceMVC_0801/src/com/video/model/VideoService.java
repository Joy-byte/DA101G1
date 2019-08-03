package com.video.model;

import java.util.List;



public class VideoService {

	private VideoDAO_interface dao;
	public VideoService() {
		dao = new VideoDAO();
	}
	
	public VideoVO addVdo(String itinerary_no, String mem_no,String[] landscape_no, List<byte[]> vdobufs) {
		VideoVO videoVO = new VideoVO();
		int i = 0;
		for(byte[] bufs:vdobufs) {			
			videoVO.setItinerary_no(itinerary_no);
			videoVO.setMem_no(mem_no);
			videoVO.setLandscape_no(landscape_no[i++]);
			videoVO.setVdo(bufs);
			dao.insert(videoVO);
			if(i>vdobufs.size())
				i=0;
		}
		return videoVO;//目前沒用,先回傳最後一筆
	}
	
	public void addIti(VideoVO videoVO) {
		dao.insert(videoVO);
	}
	

	public VideoVO getOneVdo(String itinerary_no) {
		return dao.findByPrimaryKey(itinerary_no);
	}
	
	public List<VideoVO> getAll() {
		return dao.getAll();
	}
	
	public List<VideoVO> getMemALL(String mem_no) {
		return dao.findAllByMem(mem_no);
	}
}
