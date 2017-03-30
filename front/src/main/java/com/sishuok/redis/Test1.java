package com.sishuok.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class Test1 {

	public static void main(String[] args) {
		JedisPoolConfig jcfg = new JedisPoolConfig();
		jcfg.setMaxTotal(10);
		jcfg.setMinIdle(3);
		
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(new JedisShardInfo("192.168.1.106",6379));
		list.add(new JedisShardInfo("192.168.1.106",6380));
		
		ShardedJedisPool jp = new ShardedJedisPool(jcfg, list,ShardedJedis.DEFAULT_KEY_TAG_PATTERN);

//		ShardedJedis sj = new ShardedJedis(list,ShardedJedis.DEFAULT_KEY_TAG_PATTERN);
		
		ShardedJedis sj = jp.getResource();
		
		sj.set("{K}ingabcd", "king");
		String s = sj.get("{K}ingabcd");
		System.out.println("s22=="+s+" , shard=="+sj.getShardInfo("{K}ingabcd"));
//		sj.set("{K}1", "king111111");
//		String s = sj.get("{K}1");
//		System.out.println("s22=="+s+" , shard=="+sj.getShardInfo("{K}1"));
			
//		sj.set("{K}inadfasdfdlkj", "king22222");
//		String s = sj.get("{K}inadfasdfdlkj");
//		System.out.println("s22=="+s+" , shard=="+sj.getShardInfo("{K}inadfasdfdlkj"));
		
		
		jp.returnResourceObject(sj);
		
//		Set<HostAndPort> set = new HashSet<HostAndPort>();
//		set.add(new HostAndPort("192.168.1.106",6381));
//		set.add(new HostAndPort("192.168.1.106",6382));
//		set.add(new HostAndPort("192.168.1.106",6383));
//		
//		JedisCluster jc = new JedisCluster(set,10000,20);
//		
//		String s = jc.get("k1");
//	
//		System.out.println("s111=="+s);
//		
//		JedisPoolConfig jcfg = new JedisPoolConfig();
//		jcfg.setMaxTotal(10);
//		jcfg.setMinIdle(3);
//		
//		JedisPool jp = new JedisPool(jcfg,"192.168.1.106",6379);
//		
//		Jedis jr = jp.getResource();
//		
//		String s = jr.get("k1");
//		
//		
//		jp.returnResourceObject(jr);
//		
//		System.out.println("s=="+s);
//		
//		Jedis jr = new Jedis("192.168.1.106",6379);
//		Jedis jr2 = new Jedis("192.168.1.106",6380);
//		
//		jr.set("k1","jr111");
////		System.out.println("s1==="+s1);
//		
//		jr2.slaveof("192.168.1.106",6379);
//		
//		try {
//			Thread.sleep(1000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String s2 = jr2.get("k1");
//		System.out.println("s2==="+s2);
		
		
//		Pipeline pp = jr.pipelined();
		
//		jr.watch("k1");
//		jr.set("k1","111");
		
//		Transaction pp = jr.multi();		
//		Response<String> res1 = pp.get("k1");
//		pp.set("k1","ppkkk333");
//		Response<String> res2 =pp.get("k1");
//		
//		pp.exec();
//		
////		pp.sync();
//
//		String s1 = res1.get();
//		String s2 = res2.get();
//		
//		System.out.println("s1="+s1+" , s2="+s2);
//		
//		jr.subscribe(new MyChannel(),"cc1");
		
//		
//		
//		Set<String> keys = jr.keys("*");
//		
//		SortingParams sp = new SortingParams();
//		sp.alpha();
//		sp.desc();	
//		
//		List<String> list = jr.sort("mylist",sp);
//		
////		System.out.println("s=="+s);
//		for(String s : list){
//			System.out.println("key=="+s);
//		}
//		
	}
}

class MyChannel extends JedisPubSub{
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("channel=="+channel+" , msg=="+message);
	}
}
