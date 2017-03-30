package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

public class Test2 extends RedisTemplate{
	
	public void t(){
		System.out.println("this==="+this.getConnectionFactory().getConnection());
		String s = ""+this.execute(new RedisCallback(){
			public Object doInRedis(RedisConnection jr)
					throws DataAccessException {
				System.out.println("now innnnnnnnnnnnn");
				
				return  new String(jr.get("k1".getBytes()));
			}});
		System.out.println("s=="+s);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		Test2 t = (Test2)ctx.getBean("test2");
		t.t();
	}
}