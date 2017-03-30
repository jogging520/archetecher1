package com.sishuok.architecture1.customermgr;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.customermgr.service.ICustomerService;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.customermgr.vo.CustomerQueryModel;
import com.sishuok.pageutil.Page;

//@Service
@Transactional
public class Client {
	@Autowired
	private ICustomerService s = null;
	
	public ICustomerService getS(){
		return s;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client t = (Client)ctx.getBean("client");
		System.out.println(t.getS().toString());

		CustomerModel cm = new CustomerModel();
		cm.setCustomerId("c20");
		cm.setPwd("c20");
		cm.setRegisterTime(new Date().toString());
		cm.setShowName("c20");
		cm.setTrueName("张x");
		System.out.println(cm);
        t.getS().create(cm);
		
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setNowPage(1);
		cqm.getPage().setPageShow(1);
		
System.out.println("cqm==========="+cqm);
		Page<CustomerModel> p = t.getS().getByConditionPage(cqm);
		
		System.out.println("list=="+p);
		
		
		
		cqm.getPage().setNowPage(2);
		cqm.getPage().setPageShow(1);
		Page<CustomerModel> p2 = t.getS().getByConditionPage(cqm);
		
		System.out.println("list2222=="+p);
	}
}
