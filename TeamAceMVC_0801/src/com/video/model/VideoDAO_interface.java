package com.video.model;

import java.util.List;

public interface VideoDAO_interface {
	   public void insert(VideoVO videoVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
	   public VideoVO findByPrimaryKey(String itinerary_no);
	   public List<VideoVO> findAllByMem(String mem_no);
       public List<VideoVO> getAll();
}
