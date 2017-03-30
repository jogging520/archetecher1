package com.sishuok.consistence;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishuok.architecture1.storemgr.service.StoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.interactive.InteractiveBaseController;
//
//@Controller
//@RequestMapping(value="/storeFI")
//public class StoreForInteractiveController1 extends InteractiveBaseController {
//	@Autowired
//	private StoreService ss = null;
//	@Override
//	protected Object doCall(String opeType, Map<String, Object> map) {
//		if("1".equals(opeType)){
//			//获取A商品的库存数据
//			int goodsUuid = Integer.parseInt(map.get("goodsUuid").toString());
//			StoreModel sm = ss.getByGoodsUuid(goodsUuid);
//			
//			System.out.println("sm===="+sm);
//			
//			return sm;
//		}else if("2".equals(opeType)){
//			//把新的库存数据进行调整，并更新到数据库
//			int goodsUuid = Integer.parseInt(map.get("goodsUuid").toString());
//			int adjustNum = Integer.parseInt(map.get("adjustNum").toString());
//			
//			StoreModel sm = ss.getByGoodsUuid(goodsUuid);
//			
//			sm.setStoreNum(sm.getStoreNum() - adjustNum);
//			ss.update(sm);
//		}
//		
//		return "{}";
//	}
//	
//}
