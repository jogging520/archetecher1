package com.sishuok.consistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

//	@Service
	public class MyTest {
		@Autowired
		private InteractiveCallHelper call = null;
		
		public void testCall(){
			//先处理自己的业务功能
			
			//远程调用其它模块的功能
			Map<String,Object> mapParam = new HashMap<String,Object>();
			mapParam.put("ip", "localhost");
			mapParam.put("port", "9080");
			mapParam.put("url", "/storeDispatchFI/call");
			
			
			call.call("GoodsModule", "2", mapParam);
			
			//继续处理自己的业务功能
		}
		
		public static void main(String[] args) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			MyTest test = (MyTest)ctx.getBean("myTest");
			
			test.testCall();			
		}
	}

