package com.gameroom.model;

import java.util.List;

import com.friends.model.FriendsDAO;

public class GameroomService {

	private GameroomDAO_interface dao;
	
	public GameroomService() {
		dao = new GameroomDAO();
	}
	
	public List<GameroomVO> getAll(){
		return dao.getAll();
	}
}
