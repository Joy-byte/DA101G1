package com.question.model;

import java.util.List;

public interface JedisQuestion_interface {
	  public void insert(String itineraryNo, String qustion,String qustionOption1,String qustionOption2,String qustionOption3,String answer);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public JedisQuestionVO getAll(String itineraryNo);
}
