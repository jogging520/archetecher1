package com.sishuok.shard.usermgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sishuok.shard.usermgr.vo.UserModel;

public class JdbcImpl implements UserDAO{
	private DataSource ds = null;
	private String tblName = "";
	private ApplicationContext ctx = null;
	
	public void create(UserModel um) {
		
		ds = (DataSource)ctx.getBean("ds"+"s1"); 
		tblName = "tbl_user_1";
		
		JdbcTemplate jt = new JdbcTemplate(ds);
		jt.execute("insert into "+tblName+" values("+um.getUuid()+",'"+um.getName()+"')");
		
		
	}
	
	public List<UserModel> getAll(){
		List<UserModel> retList = new ArrayList<UserModel>();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("s1", "tbl_user_0,tbl_user_1");
//		map.put("s2", "tbl_user");
		
		Set<String> set = map.keySet();
		
		for(String dsName : set){
			ds = (DataSource)ctx.getBean("ds"+dsName);
			JdbcTemplate jt = new JdbcTemplate(ds);
			String tblNames = map.get(dsName);
			if(tblNames.contains(",")){
				String[] ss = tblNames.split(",");
				for(String s : ss){
					System.out.println("sss======="+"select uuid,name from "+s);
//					List<UserModel> tempList = jt.queryForList("select uuid,name from "+s,UserModel.class);
//					retList.addAll(tempList);
					
					List<Map<String,Object>> temp = jt.queryForList("select uuid,name from "+s);
					System.out.println("temp=="+temp);
				}
			}
		}
		System.out.println("retList=="+retList);	
		return retList;
	}
	
	public static void main(String[] args) {
		JdbcImpl t = new JdbcImpl();
		
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
		t.ctx = appCtx;
		
		UserModel um = new UserModel();
		um.setUuid(11);
		um.setName("name11");
		
//		t.create(um);
		t.getAll();
		
	}

}
