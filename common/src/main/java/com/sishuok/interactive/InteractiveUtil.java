package com.sishuok.interactive;

import com.alibaba.fastjson.JSON;

/**
 * 模块交互调用的辅助工具类
 */
public class InteractiveUtil {
	private InteractiveUtil(){}
	
	public static InteractiveModel paramJson2Model(String jsonParam){
		return JSON.parseObject(jsonParam, InteractiveModel.class);
	}
	
	public static <T> T retJson2Model(String json,Class<T> cls){
		return JSON.parseObject(json,cls);
	}
	
	
}
