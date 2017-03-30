package com.sishuok.architecture1.goodsmgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

//@Repository
public interface GoodsRedisDAO extends GoodsDAO{
	public Page<GoodsModel> getByCondition(GoodsQueryModel qm,int nowPage,int pageShow);
	public List<GoodsModel> getByIds(String ids);
}
