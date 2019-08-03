package com.itinerary.model;

import java.util.List;

import com.itinerary_detail.model.Itinerary_detailService;
import com.landscape.model.LandscapeVO;
import com.picture.model.PictureService;
import com.question.model.JedisQuestionPool;
import com.video.model.VideoService;



public class ItineraryService {

	private ItineraryDAO_interface dao;
	private Itinerary_detailService itinerary_detailService;
	private PictureService pictureService;
	private VideoService videoService;
	private JedisQuestionPool jedisQuestionPool;
	public ItineraryService() {
		dao = new ItineraryDAO();
		itinerary_detailService = new Itinerary_detailService();
		pictureService=new PictureService();
		videoService=new VideoService();
		jedisQuestionPool = new JedisQuestionPool();
	}
	
	public ItineraryVO addIti(String mem_no, String itinerary_status, String landscape_no[], List<byte[]>picbufs,
			List<byte[]>vdobufs, String qustion,String qustionOption1,String qustionOption2,String qustionOption3,String answer) {
		ItineraryVO itineraryVO = new ItineraryVO();
		itineraryVO.setMem_no(mem_no);
		itineraryVO.setItinerary_status(itinerary_status);
		String key=dao.insert(itineraryVO);
		itinerary_detailService.addItd(key,landscape_no,"");
		pictureService.addPic(key, mem_no, landscape_no, picbufs);
		videoService.addVdo(key, mem_no, landscape_no, vdobufs);
		jedisQuestionPool.insert(key,qustion,qustionOption1,qustionOption2,qustionOption3,answer);
		return itineraryVO;
	}
	
	public void addIti(ItineraryVO itineraryVO) {
		dao.insert(itineraryVO);
	}
	

	public List<ItineraryVO> getAll() {
		return dao.getAll();
	}

	
	public List<ItineraryVO> getLastF() {
		return dao.getLastFour();
	}
	
	public List<ItineraryVO> getItineraryByMemNo(String mem_no) {
		return dao.getByMemNo(mem_no);
	}
	
	public ItineraryVO getOne(String itinerary_no) {
		return dao.findByPrimaryKey(itinerary_no);
	}
}
