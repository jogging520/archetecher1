package com.sishuok.architecture1.goodsmgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.json.JsonHelper;

//@Service
//@Primary
public class GoodsRedisImpl implements GoodsRedisDAO{

	public void create(GoodsModel m) {
		JedisPool jp = RedisHelper.getPool();
		Jedis jr = jp.getResource();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("uuid", ""+m.getUuid());
		map.put("name", m.getName());
		map.put("imgPath", m.getImgPath());
		map.put("description", m.getDescription());
		
		jr.hmset("GoodsH:"+m.getUuid(), map);
		
		jr.lpush("GoodsUuidsL",""+m.getUuid());
		
		jp.returnResourceObject(jr);
	}

	public void update(GoodsModel m) {
		JedisPool jp = RedisHelper.getPool();
		Jedis jr = jp.getResource();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("uuid", ""+m.getUuid());
		map.put("name", m.getName());
		map.put("imgPath", m.getImgPath());
		map.put("description", m.getDescription());
		
		jr.hmset("GoodsH:"+m.getUuid(), map);
		
		jp.returnResourceObject(jr);
	}

	public void delete(int uuid) {
		JedisPool jp = RedisHelper.getPool();
		Jedis jr = jp.getResource();
		
		jr.del("GoodsH:"+uuid);
		
		jr.lrem("GoodsUuidsL", 1, ""+uuid);
		
		jp.returnResourceObject(jr);
	}

	public GoodsModel getByUuid(int uuid) {
		JedisPool jp = RedisHelper.getPool();
		Jedis jr = jp.getResource();
		
		Map<String,String> map = jr.hgetAll("GoodsH:"+uuid);
		GoodsModel gm = new GoodsModel();
		
		gm.setDescription(map.get("description"));
		gm.setImgPath(map.get("imgPath"));
		gm.setName(map.get("name"));
		gm.setUuid(uuid);
		
		jp.returnResourceObject(jr);
		
		return gm;
	}

	public Page<GoodsModel> getByCondition(GoodsQueryModel qm,int nowPage,int pageShow){
		List<GoodsModel> retList = new ArrayList<GoodsModel>();
		
		JedisPool jp = RedisHelper.getPool();
		Jedis jr = jp.getResource();
		
		List<String> keys = new ArrayList<String>();
		List<String> args = new ArrayList<String>();

		args.add(JsonHelper.object2str(qm));
		
		Object obj = jr.eval(RedisHelper.getScript("GoodsDAOGetByCondition.lua"), keys, args);
		
		List<String> list = (List<String>)obj;
		for(String s : list){
			if(s.indexOf("totalCount") < 0){
//				GoodsModel gm = (GoodsModel)JsonHelper.str2Object(s, GoodsModel.class);
//				retList.add(gm);
			}else{
				int begin = s.indexOf("totalCount=");
				int end = s.lastIndexOf("}");
				String countStr = s.substring(begin+"totalCount=".length(),end);

				qm.getPage().setTotalCount(Integer.parseInt(countStr));
			}
		}

		args.clear();
		qm.getPage().setNowPage(nowPage);
		qm.getPage().setPageShow(pageShow);
		
		args.add(JsonHelper.object2str(qm));
		
		Object obj2 = jr.eval(RedisHelper.getScript("GoodsDAOGetByCondition.lua"), keys, args);
		
		List<String> list2 = (List<String>)obj2;
		for(String s : list2){
			if(s.indexOf("totalCount") < 0){
				GoodsModel gm = (GoodsModel)JsonHelper.str2Object(s, GoodsModel.class);
				retList.add(gm);
			}
		}
		
		jp.returnResourceObject(jr);
		
		qm.getPage().setResult(retList);
		
		return qm.getPage();
	}
	public static void main(String[] args) {
		GoodsRedisImpl t = new GoodsRedisImpl();
		
//		GoodsModel gm = new GoodsModel();
//		gm.setUuid(3);
//		gm.setName("n23");
//		gm.setImgPath("p3");
//		gm.setDescription("d3");
//		
//		t.create(gm);
		
		
		
		
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.setUuid(0);
		qm.setName("");
		qm.setImgPath("");
		qm.setDescription("");
		
		Page<GoodsModel> list = t.getByCondition(qm,2,1);
		System.out.println("list=="+list);
	}

	public List<Integer> getIdsByConditionPage(GoodsQueryModel gqm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GoodsModel> getByIds(String ids) {
		
		return null;
	}

	public List<GoodsModel> getByConditionPage(GoodsQueryModel qm) {
		return this.getByCondition(qm,qm.getPage().getOldNowPage(), qm.getPage().getPageShow()).getResult();
	}

}
