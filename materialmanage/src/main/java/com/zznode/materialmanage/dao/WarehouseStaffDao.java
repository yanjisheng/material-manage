package com.zznode.materialmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.zznode.materialmanage.model.WarehouseStaff;

/**
 * 仓库与员工对应关系
 * @author yanjisheng
 *
 */
@Mapper
public interface WarehouseStaffDao {

	public int addOne(WarehouseStaff warehouseStaff);
	
	public WarehouseStaff selectById(int id);
	
	public List<WarehouseStaff> selectByCondition(WarehouseStaff condition);
	
	public int deleteById(int id);
}
