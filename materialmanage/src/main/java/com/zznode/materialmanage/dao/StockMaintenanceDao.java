package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zznode.materialmanage.model.StockMaintenance;
import com.zznode.materialmanage.model.StockMaintenanceQuery;

/**
 * 返修单
 * @author yanjisheng
 *
 */
@Mapper
public interface StockMaintenanceDao {

	public int addStockMaintenance(StockMaintenance stockMaintenance);
	
	public StockMaintenance selectById(int stockMaintenanceId);
	
	public List<StockMaintenance> selectByCondition(StockMaintenanceQuery condition);
}
