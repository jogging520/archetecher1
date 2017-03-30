package com.sishuok.interactive.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

@Service
public class InteractiveTest {
	@Autowired
	private InteractiveCallHelper call = null;
	
	public void testCall(){
		//先处理自己的业务功能
		
		//远程调用其它模块的功能
		Map<String,Object> mapParam = new HashMap<String,Object>();
		mapParam.put("abc", "aa11");
		mapParam.put("paramName", "testName");
		
		MyRetModel rm = call.call("GoodsModule", "2", mapParam,MyRetModel.class);
		
		System.out.println("rm==="+rm);
		//继续处理自己的业务功能
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		InteractiveTest test = (InteractiveTest)ctx.getBean("interactiveTest");
		
		test.testCall();
		
	}
}
