package com.sishuok.architecture1.storemgr.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sishuok.architecture1.storemgr.service.StoreService;
import com.sishuok.interactive.InteractiveBaseController;


@Controller
@RequestMapping(value="/storeFI")
public class StoreForInteractiveController extends InteractiveBaseController {
	@Autowired
	private StoreService ss;
	
	@Override
	protected Object doCall(String opeType, Map<String, Object> map) {
		System.out.println("now 处理 opeType=="+opeType+", goodsUuid="+map.get("goodsUuid"));
		//根据opeType来转调逻辑层的业务方法
		boolean flag = false;
		StoreFIRetModel rm = new StoreFIRetModel();
		try{
			if("1".equals(opeType)){			
				int goodsUuid = Integer.parseInt(""+map.get("goodsUuid"));
				int needSubNum = Integer.parseInt(""+map.get("needSubNum"));
				
				flag = ss.subStore(goodsUuid, needSubNum);			
			}
			//准备一个测试数据来返回
			
			rm.setCallResult(flag);
		}catch(Exception err){
			err.printStackTrace();
		}
		return rm;
	}
	
}
