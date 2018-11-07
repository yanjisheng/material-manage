package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockUse;
import com.zznode.materialmanage.model.StockUseQuery;

/**
 * 领用单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockUseDao {

	public int addStockUse(StockUse stockUse);
	
	public int updateStockUse(StockUse stockUse);
	
	public StockUse selectById(int stockUseId);
	
	public List<StockUse> selectByCondition(StockUseQuery condition);
}
