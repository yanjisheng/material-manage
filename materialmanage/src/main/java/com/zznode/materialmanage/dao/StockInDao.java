package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zznode.materialmanage.model.StockIn;
import com.zznode.materialmanage.model.StockInQuery;

/**
 * 入库单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockInDao {

	public int addStockIn(StockIn stockIn);
	
	public int addStockInSnList(StockIn stockIn);
	
	public StockIn selectById(int id);
	
	public List<StockIn> selectByCondition(@Param("condition") StockInQuery condition);
	
}
