package com.sishuok.interactive;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 进行模块间交互调用的基础控制器
 */
public abstract class InteractiveBaseController {
	
	@RequestMapping("/call")
	@ResponseBody
	public Object call(@RequestParam("jsonParam") String jsonParam){
		if(jsonParam!=null && jsonParam.contains("*")){
			jsonParam = jsonParam.replace("*", "#");
		}
		InteractiveModel im = InteractiveUtil.paramJson2Model(jsonParam);
		//去做真正的业务
		Object ret = doCall(im.getOpeType(),im.getMap());
		return ret;
	}
	
	protected abstract Object doCall(String opeType,Map<String,Object> map);
}
