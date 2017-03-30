package com.sishuok.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.goodsmgr.service.GoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.storemgr.service.StoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.architecture1.storemgr.vo.StoreQueryModel;
import com.sishuok.pageutil.Page;

@Service
public class TransactionTest2 {
	@Autowired
	private StoreService ss;
	@Autowired
	private GoodsService gs;
	
	public void testAdd(){
		Page p = ss.getByConditionPage(new StoreQueryModel());
		System.out.println("p==="+p.getResult());
		
		StoreModel sm = new StoreModel();
		sm.setUuid(8);
		sm.setGoodsUuid(8);
		sm.setStoreNum(8);
		
		ss.create(sm);
		
		GoodsModel gm = new GoodsModel();
		gm.setUuid(8);
		gm.setImgPath("8");
		gm.setName("8");
		gm.setDescription("8");
		
		gs.create(gm);
		
		
//		int i = 5/0;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml","applicationContext-tx.xml");
		
		TransactionTest2 test = (TransactionTest2)ctx.getBean("transactionTest2");
		
		test.testAdd();	
	}

}
