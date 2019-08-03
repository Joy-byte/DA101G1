package com.friends.model;

import java.io.Serializable;

public class FriendsVO implements Serializable{
	private String friends_no;
	private String mem_no;
	
	
	
	public String getFriends_no() {
		return friends_no;
	}
	public void setFriends_no(String friends_no) {
		this.friends_no = friends_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
	
}
