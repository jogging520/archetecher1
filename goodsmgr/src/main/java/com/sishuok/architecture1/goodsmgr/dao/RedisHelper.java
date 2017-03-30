package com.sishuok.architecture1.goodsmgr.dao;

import java.io.File;
import java.io.FileInputStream;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHelper {
	private RedisHelper(){
		
	}
	private static JedisPool jp = null;
	public static JedisPool getPool(){
		if(jp==null){
			JedisPoolConfig jcfg = new JedisPoolConfig();
			jcfg.setMaxTotal(10);
			jcfg.setMinIdle(3);
			
			jp = new JedisPool(jcfg,"192.168.1.106",6379);
		}
		return jp;
	}
	
	public static String getScript(String str){
		File f = new File(str);
		try{
			FileInputStream fin = new FileInputStream(f);
			
			byte[] bs = new byte[fin.available()];
			
			fin.read(bs);
			
			fin.close();
			
			return new String(bs);
		}catch(Exception err){
			err.printStackTrace();
		}
		return "";
	}

}
