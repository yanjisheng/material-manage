package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockReuse;
import com.zznode.materialmanage.model.StockReuseQuery;

/**
 * 备件单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockReuseDao {

	public int addStockReuse(StockReuse stockReuse);
	
	public StockReuse selectById(int stockReuseId);
	
	public List<StockReuse> selectByCondition(StockReuseQuery condition);
}
