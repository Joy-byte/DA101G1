package com.question.model;



import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisQuestionPool implements JedisQuestion_interface {

	
//public static void main(String args[]) {
//	pool = JedisUtil.getJedisPool();
//	Jedis jedis = pool.getResource();
//	jedis.auth("misj");
//	
//	jedis.flushDB();
//			
//	HashMap<String, String> data = new HashMap<>();
//	data.put("brand", "Pentel");
//	data.put("price", "50");
//	// 無須對應ORM設計，可自由為key增減自己需要的欄位與欄位值
//	data.put("color", "blue");
//	
//	jedis.hmset("pen:2", data);
//	List<String> penData = jedis.hmget("pen:2", "brand", "price", "color");
//	for (String str : penData)
//		System.out.println(str);
	
//	Map<String, String> hAll = jedis.hgetAll("IT00005");
//	for (String str : hAll.keySet())
//		System.out.println(str + " = " + hAll.get(str));
	
	// 註：Hash沒有提供hincr(遞增)指令，只有hincrby指令可用
	
//	jedis.close();
//	JedisUtil.shutdownJedisPool();
//}


	@Override
	public void insert(String itineraryNo,String qustion,String qustionOption1,String qustionOption2,String qustionOption3,String answer){

		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");	
		
		
				
		HashMap<String, String> data = new HashMap<>();
//		data.put("itineraryNo", itineraryNo);
		data.put("qustion", qustion);
		data.put("qustionOption1", qustionOption1);
		// 無須對應ORM設計，可自由為key增減自己需要的欄位與欄位值
		data.put("qustionOption2", qustionOption2);
		data.put("qustionOption3", qustionOption3);
		data.put("answer", answer);
		
		jedis.hmset(itineraryNo, data);

		
		jedis.close();
	}
	
	@Override
	public JedisQuestionVO getAll(String itineraryNo) {

	
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");	
		

	   JedisQuestionVO jedisQuestionVO = new JedisQuestionVO();

	    Map<String, String> hAll = jedis.hgetAll(itineraryNo);
		for (String str : hAll.keySet()) {
			System.out.println(str + " = " + hAll.get(str));
		
			if(str.equals("qustion"))
				jedisQuestionVO.setQusetion(hAll.get(str));	
			if(str.equals("qustionOption1"))
				jedisQuestionVO.setQusetionOption1(hAll.get(str));
			if(str.equals("qustionOption2"))
				jedisQuestionVO.setQusetionOption2(hAll.get(str));
			if(str.equals("qustionOption3"))
				jedisQuestionVO.setQusetionOption3(hAll.get(str));
			if(str.equals("answer"))
				jedisQuestionVO.setAnswer(hAll.get(str));
		}
		
		
		jedis.close();
		return jedisQuestionVO;
	}
}


