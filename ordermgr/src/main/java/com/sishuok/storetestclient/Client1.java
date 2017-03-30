package com.sishuok.storetestclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

@Service
public class Client1 {
	@Autowired
	private InteractiveCallHelper call = null;
	
	public void testCall(int goodsUuid,int num,int port){
		//先处理自己的业务功能
		
		//远程调用其它模块的功能
		Map<String,Object> mapParam = new HashMap<String,Object>();
		mapParam.put("goodsUuid", ""+goodsUuid);
		mapParam.put("needSubNum", ""+num);
		
		mapParam.put("ip", "localhost");
		mapParam.put("port", ""+port);
		mapParam.put("url", "/storemgr/storeFI/call");
		
		ResultModel rm = call.call("StoreModule", "1", mapParam,ResultModel.class);
		
//		String str = call.call("StoreModule", "1", mapParam);
		
		System.out.println("rm==="+rm);
		//继续处理自己的业务功能
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Client1 test = (Client1)ctx.getBean("client1");
		int port = 8080;
		for(int i=1;i<=6;i++){
			if(i>10){
				port = 9080;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test.testCall(i,2,port);
		}
		
	}
}
