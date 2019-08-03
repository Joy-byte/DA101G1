package com.friends.model;



import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;


public class JedisFriendPool implements JedisFriend_interface {

	
public static void main(String args[]) {
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("misj");	
	
	String A = "MB00001";
	String B = "IT00023";
	String C= "8";
	jedis.sadd(A+":"+B, C);
	jedis.close();
//	for (String str : jedis.smembers("MB00001:Friends")) {
//		System.out.println(str);
//	}
//
//	jedis.close();
//	
//	FriendsVO friendsVO = new FriendsVO();
//	friendsVO.setFriends_no("MB00005");
//	friendsVO.setMem_no("MB00006");
//	JedisFriendPool j=new JedisFriendPool();
//	j.insert(friendsVO);
//	FriendsVO friendsVO = new FriendsVO();
	
//	JedisFriendPool j=new JedisFriendPool();
//	for()
//	System.out.println(j.getAll("MB00001"));
	
}


	@Override
	public void  insert(FriendsVO friendsVO){
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");	
		jedis.sadd(friendsVO.getMem_no()+":Friends",friendsVO.getFriends_no());	
		jedis.close();
	}
	
	@Override
	public List<FriendsVO> getAll(String mem_no) {

	
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");	
		
		List<FriendsVO> list = new ArrayList<>();
		
		for (String str : jedis.smembers(mem_no+":Friends")) {
			FriendsVO friendsVO = new FriendsVO();
			friendsVO.setFriends_no(str);
			list.add(friendsVO);
	    }
		
		jedis.close();
		return list;
	
  }
}

