package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zznode.materialmanage.model.Warehouse;
import com.zznode.materialmanage.model.WarehouseQuery;

/**
 * 仓库
 * @author yanjisheng
 *
 */
@Mapper
public interface WarehouseDao {

	public Warehouse selectById(int warehouseId);
	
	public List<Warehouse> selectByCondition(WarehouseQuery condition);
	
	public int addOne(Warehouse warehouse);
	
	public int deleteById(int warehouseId);
	
	public int updateStatus(@Param("warehouseId") int warehouseId, @Param("status") byte status);
}
