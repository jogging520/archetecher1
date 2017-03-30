package com.sishuok.mem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.whalin.MemCached.MemCachedClient;

//@Service
public class TestMem {
	//@Autowired
	private MemCachedClient mcc;
	
	public void t1(){
		for(int i=0;i<10;i++){
//			mcc.add("key"+i, "value"+i);
			
			String s = (String)mcc.get("key"+i);
			System.out.println("key"+i+",value="+s);
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		TestMem t = (TestMem)ctx.getBean("testMem");
		t.t1();
	}
}
