package com.condition.model;

import java.util.List;

public interface ConditionDAO_interface {
	  public void insert(ConditionVO conditionVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<ConditionVO> getAll();
}
