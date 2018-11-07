package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockMove;
import com.zznode.materialmanage.model.StockMoveQuery;

/**
 * 调拨单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockMoveDao {

	public int addStockMove(StockMove stockMove);
	
	public int addStockMoveSnList(StockMove stockMove);
	
	public StockMove selectById(int id);
	
	public List<StockMove> selectByCondition(StockMoveQuery condition);
}
