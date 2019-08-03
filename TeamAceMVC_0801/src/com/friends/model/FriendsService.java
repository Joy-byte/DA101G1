package com.friends.model;

import java.util.List;

public class FriendsService {
	
	private Friends_interface dao;
	
	public FriendsService() {
		dao = new FriendsDAO();
	}
	
	public FriendsVO addFriends(String mem_no) {
		FriendsVO friendsVO = new FriendsVO();
		
		friendsVO.setMem_no(mem_no);
		dao.insert(friendsVO);
		
		return friendsVO;
	}
	
	public FriendsVO updateFriends(String mem_no,String friends_no) {
		FriendsVO friendsVO = new FriendsVO();
		
		friendsVO.setFriends_no(friends_no);
		friendsVO.setMem_no(mem_no);
		dao.update(friendsVO);
		
		return friendsVO;
	}
	
	public void deleteFriends(String friends_no) {
		dao.delete(friends_no);
		
	}
	public FriendsVO getOneFriends(String friends_no) {
		return dao.findByPrimaryKey(friends_no);
	}
	public List<FriendsVO> getAll(){
		return dao.getAll();
	}
	

}
