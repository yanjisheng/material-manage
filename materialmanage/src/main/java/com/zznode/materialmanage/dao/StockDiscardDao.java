package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockDiscard;
import com.zznode.materialmanage.model.StockDiscardQuery;

/**
 * 报废单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockDiscardDao {

	public int addStockDiscard(StockDiscard stockDiscard);
	
	public StockDiscard selectById(int stockDiscardId);
	
	public List<StockDiscard> selectByCondition(StockDiscardQuery condition);
}
