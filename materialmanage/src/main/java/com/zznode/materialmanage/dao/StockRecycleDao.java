package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockRecycle;
import com.zznode.materialmanage.model.StockRecycleQuery;

/**
 * 回收单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockRecycleDao {
	
	public int addStockRecycle(StockRecycle stockRecycle);
	
	public int updateStockRecycle(StockRecycle stockRecycle);
	
	public StockRecycle selectById(int stockRecycleId);
	
	public List<StockRecycle> selectByCondition(StockRecycleQuery condition);
}
