package com;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.SortingParams;

public class Test1 {
	public static void main(String[] args) {
		Jedis t = new Jedis("192.168.1.106", 6379);
		
		t.set("k1","11");
		
		String s = t.get("k1");
//		t.lpush("mylist", "12a","2a","34","23");
		
		
		SortingParams sp = new SortingParams();
		sp.alpha();
		sp.desc();
//		Transaction tt = t.multi();
		
		
		
		
		List<String> list = t.sort("mylist",sp);
		for(String s1 : list){
			System.out.println("s11==="+s1);
		}
		
		System.out.println("s="+s);
		
//		MyChannel myc = new MyChannel();
//		t.subscribe(myc, "c1");
//		
//		t.publish("c1", "mmm111");
		
		Pipeline p=t.pipelined();
		Response<String> k1 = p.get("k1");
		Response<String> k2 = p.get("k2");
		p.sync();
		
		String s1 = k1.get();
		String s2 = k2.get();
		System.out.println("k1="+s1);
		
//		JedisPool jp = new JedisPool();
//		Jedis j = jp.getResource();
//		
//		jp.returnResourceObject(j);
//		JedisPoolConfig jpc = new JedisPoolConfig();
		String script = readFile();
		System.out.println("script=="+script);
		List<String> as = new ArrayList<String>();
		as.add("k2");
		Object ret = t.eval(script,new ArrayList<String>(),as);
		System.out.println("ret==="+ret);
	}

	private static String readFile(){
		try{
			InputStream in = new FileInputStream("a.lua");
			byte[] bs = new byte[in.available()];
			
			in.read(bs);
			
			return new String(bs);
		}catch(Exception err){
			err.printStackTrace();
		}
		return "";
	}
}


class MyChannel extends JedisPubSub{
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("channel="+channel+" , msg="+message);
	}
	
}