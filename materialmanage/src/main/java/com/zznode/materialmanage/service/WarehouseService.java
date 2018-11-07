package com.zznode.materialmanage.service;

import java.util.List;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.model.Warehouse;
import com.zznode.materialmanage.model.WarehouseQuery;

/**
 * 仓库
 * @author yanjisheng
 *
 */
public interface WarehouseService {

	/**
	 * 按仓库id查询仓库信息
	 */
	public Warehouse selectById(int warehouseId);
	
	/**
	 * 按仓库类型、省、市编码和地址查询仓库信息
	 */
	public PageSerializable<Warehouse> selectByCondition(WarehouseQuery condition, PageQueryModel pageQuery);
	
	/**
	 * 添加仓库信息
	 */
	public int addNew(Warehouse warehouse);
	
	/**
	 * 从数据库中彻底删除仓库信息（不可恢复）
	 */
	public int deleteById(int warehouseId);
	
	/**
	 * 设置仓库在用状态
	 */
	public int setStatus(int warehouseId, byte status);
	
	/**
	 * 查询仓库历史
	 */
	public List<Warehouse> queryHistory(int warehouseId);
	
	//TODO 这里没有更新仓库信息的方法，请考虑清楚是否需要该方法
}
