package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zznode.materialmanage.model.Stock;
import com.zznode.materialmanage.model.StockQuery;

/**
 * 终端库存
 * @author yanjisheng
 *
 */
@Mapper
public interface StockDao {

	public int addStock(Stock stock);
	
	public int addStockList(@Param("stockList") List<Stock> stockList);
	
	public Stock selectById(int terminalId);
	
	public Stock selectBySn(String terminalSn);
	
	public List<Stock> selectByCondition(@Param("condition") StockQuery condition);
	
	public int updateStock(Stock stock);
}
