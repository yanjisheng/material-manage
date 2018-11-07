package com.zznode.materialmanage.service;

import java.util.List;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.model.WarehouseStaff;

/**
 * 仓库与员工对应关系
 * @author yanjisheng
 *
 */
public interface WarehouseStaffService {

	/**
	 * 添加一条仓库与员工对应关系
	 */
	public void addOne(WarehouseStaff warehouseStaff);
	
	/**
	 * 按id查询仓库与员工对应关系
	 */
	public WarehouseStaff selectById(int id);
	
	/**
	 * 查询某仓库对应的员工
	 */
	public List<WarehouseStaff> selectByWarehouseId(int warehouseId);
	
	/**
	 * 查询某员工对应的仓库
	 */
	public List<WarehouseStaff> selectByUserId(int userId);
	
	/**
	 * 分页查询各仓库的管理员
	 */
	public PageSerializable<WarehouseStaff> selectManager(PageQueryModel pageQuery);
	
	/**
	 * 按id删除仓库与员工对应关系
	 */
	public void deleteById(int id);
}
