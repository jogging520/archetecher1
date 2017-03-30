package com.sishuok.architecture1.storemgr.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.storemgr.amqhelper.StoreSender;
import com.sishuok.architecture1.storemgr.dao.StoreDAO;
import com.sishuok.architecture1.storemgr.redishelper.RedisHelper;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.architecture1.storemgr.vo.StoreQueryModel;

@Service
@Transactional
public class StoreService extends BaseService<StoreModel,StoreQueryModel> implements IStoreService{
	//@Autowired
	//private StoreSender sender;
	
	private StoreDAO dao = null;
	@Autowired
	private void setDao(StoreDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	public StoreModel getByGoodsUuid(int goodsUuid) {
		return dao.getByGoodsUuid(goodsUuid);
	}
//	public synchronized boolean subStore(int goodsUuid, int num) {
//		try{
//			//1:根据业务数据的拆分规则，寻找需要操作的redis服务器
//			int port = 6379;
//			if(goodsUuid>5 && goodsUuid<=10){
//				port = 6387;
//			}
//			if(goodsUuid>10 && goodsUuid<=15){
//				port = 6388;
//			}
//			if(goodsUuid>15 && goodsUuid<=20){
//				port = 6389;
//			}
//			
//			System.out.println("goodsUuid="+goodsUuid+",port="+port);
//			//2：从redis服务器上获取当前的库存值
//			int redisNum = this.getStoreNum(port, goodsUuid);
//			
//			//3：判断库存是否足够
//			if(redisNum < num){
//				return false;
//			}
//			//4：减库存，然后写回到redis服务器
//			int newNum = redisNum - num;
//			this.setStoreNum(port, goodsUuid, newNum);
//			
//			//5：发异步消息去把数据更新到数据库
//			StoreModel sm = new StoreModel();
//			sm.setGoodsUuid(goodsUuid);
//			sm.setStoreNum(newNum);
//			
//			sender.sendMsg(sm,"OP_Type_Sub_Store");
//		}catch(Exception err){
//			err.printStackTrace();
//		}
//		return true;
//	}
//	private void setStoreNum(int port,int goodsUuid,int newNum){
//		JedisPool jp = RedisHelper.getPool(port);
//		Jedis jr = jp.getResource();
//		
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("goodsUuid", ""+goodsUuid);
//		map.put("storeNum", ""+newNum);
//		
//		jr.hmset("StoresH:"+goodsUuid, map);
//		
//		jp.returnResourceObject(jr);
//	}
//	private int getStoreNum(int port,int goodsUuid){
//		JedisPool jp = RedisHelper.getPool(port);
//		Jedis jr = jp.getResource();
//		
//		Map<String,String> map = jr.hgetAll("StoresH:"+goodsUuid);
//		
//		int num = Integer.parseInt(""+map.get("storeNum"));
//		
//		jp.returnResourceObject(jr);
//		
//		return num;
//	}
	@Override
	public boolean subStore(int goodsUuid, int num) {
		// TODO Auto-generated method stub
		StoreModel sm = new StoreModel();
		sm=dao.getByGoodsUuid(goodsUuid);
		if(sm.getStoreNum()-num>0) {
			sm.setStoreNum(sm.getStoreNum()-num);
			dao.create(sm);
			return true;
		}
		return false;
	}
	
}