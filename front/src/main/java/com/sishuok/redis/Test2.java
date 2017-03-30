package com.sishuok.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class Test2 extends RedisTemplate{
	
	public void t1(){

		Object ret = this.execute(new RedisCallback(){
			public Object doInRedis(RedisConnection jr)
					throws DataAccessException {
				String s = new String(jr.get("k1".getBytes()));
				
				return s;
			}});
		
		System.out.println("spring s==="+ret);
	}
	
	public static void main(String[] args) {
//		Jedis jr = new Jedis("192.168.1.106",6379);
//		jr.publish("cc1", "hello , I'm test2");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		Test2 t = (Test2)ctx.getBean("testClient");
		
		t.t1();
	}
}
