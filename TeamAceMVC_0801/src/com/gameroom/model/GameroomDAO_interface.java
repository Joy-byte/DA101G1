package com.gameroom.model;

import java.util.List;

public interface GameroomDAO_interface {
//	  public void insert(LandScapeVO landScapeVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<GameroomVO> getAll();
}
