package com.oneononechat.controller;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisMessageAction {
//	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
//		Jedis jedis = null;
//		jedis = pool.getResource();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");
		List<String> historyData = jedis.lrange(key, 0, jedis.llen(key) - 1);
		jedis.close();
		return historyData;
	}
	
	public static Set<String> getChatMember(String mem_no) {
		String key = new StringBuilder(mem_no).append(":").append("chat").toString();
//		Jedis jedis = null;
//		jedis = pool.getResource();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");
		Set<String> chatMember = jedis.smembers(key);
		jedis.close();
		return chatMember;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		String senderKey2 = new StringBuilder(sender).append(":").append("chat").toString();
		String receiverKey2 = new StringBuilder(receiver).append(":").append("chat").toString();
//		Jedis jedis = null;
//		jedis = pool.getResource();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("misj");
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
		//存入聊過天的會員
		jedis.sadd(senderKey2, receiver);
		jedis.sadd(receiverKey2, sender);

		jedis.close();
	}

}
