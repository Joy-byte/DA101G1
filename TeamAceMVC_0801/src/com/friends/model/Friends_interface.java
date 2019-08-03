package com.friends.model;

import java.util.List;

public interface Friends_interface {
	public void insert(FriendsVO FriendsVO);
	public void update(FriendsVO FriendsVO);
	public void delete(String friends_no);
	public FriendsVO findByPrimaryKey(String friends_no);
	public List<FriendsVO> getAll();
}
