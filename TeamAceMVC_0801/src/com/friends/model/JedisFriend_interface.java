package com.friends.model;

import java.util.List;

import com.landscape.model.LandscapeVO;

public interface JedisFriend_interface {
	  public void insert(FriendsVO friendsVO);
//    public void update(LandScapeVO landScapeVO);
//    public void delete(Integer landScapeVO);
//    public LandScapeVO findByPrimaryKey(Integer landScape_no);
      public List<FriendsVO> getAll(String mem_no);
}
