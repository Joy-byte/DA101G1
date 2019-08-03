package com.oneononechat.controller;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisPoolUtil {
	private static JedisPool pool = null;

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPool() {
		if (pool == null) {
			synchronized (JedisPoolUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(8);
					config.setMaxIdle(8);
					config.setMaxWaitMillis(30000);
					pool = new JedisPool(config, "localhost", 6379);
//					pool = new JedisPool(config, "10.120.39.13", 6379);
				}
			}
		}
		return pool;
	}

	// �i�bServletContextListener��contextDestroyed�̩I�s����k���PRedis�s�u��
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}

