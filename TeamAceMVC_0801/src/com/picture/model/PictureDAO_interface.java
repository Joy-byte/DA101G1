package com.picture.model;

import java.util.List;

public interface PictureDAO_interface {
	   public void insert(PictureVO pictureVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
	   public PictureVO findByPrimaryKey(String itinerary_no);
	   public PictureVO findByItiAndLs(String itinerary_no,String landscape_no);
	   public List<PictureVO> findByMemId(String mem_no);
       public List<PictureVO> getAll();
}
