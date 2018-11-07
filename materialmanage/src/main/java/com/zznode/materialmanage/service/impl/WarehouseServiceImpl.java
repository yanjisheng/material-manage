package com.zznode.materialmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.dao.WarehouseDao;
import com.zznode.materialmanage.model.Warehouse;
import com.zznode.materialmanage.model.WarehouseQuery;
import com.zznode.materialmanage.service.WarehouseService;

/**
 * 仓库管理
 * @author yanjisheng
 *
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	private static Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);
	
	@Autowired
	WarehouseDao dao;

	@Override
	public Warehouse selectById(int warehouseId) {
		log.info("按id["+warehouseId+"]查询仓库");
		return dao.selectById(warehouseId);
	}

	@Override
	public PageSerializable<Warehouse> selectByCondition(WarehouseQuery condition, PageQueryModel pageQuery) {
		log.info("按条件分页查询仓库");
		if(StringUtils.isEmpty(pageQuery.getOrderBy())){
			pageQuery.setOrderBy("createTime DESC");
		}
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<Warehouse> result = dao.selectByCondition(condition);	
		return new PageSerializable<Warehouse>(result);
	}

	@Override
	public int addNew(Warehouse warehouse) {
		log.info("添加仓库["+warehouse.getWarehouseName()+"]");
		return dao.addOne(warehouse);
	}

	@Override
	public int deleteById(int warehouseId) {
		log.info("按id["+warehouseId+"]删除仓库");
		return dao.deleteById(warehouseId);
	}

	@Override
	public int setStatus(int warehouseId, byte status) {
		log.info("设置仓库["+warehouseId+"]状态");
		return dao.updateStatus(warehouseId, status);
	}
	
	@Override
	public List<Warehouse> queryHistory(int warehouseId) {
		log.info("查询仓库["+warehouseId+"]修改历史");
		List<Warehouse> result = new ArrayList<Warehouse>();
		do{
			Warehouse item = dao.selectById(warehouseId);
			if(item == null){
				break;
			}else if(item.getOldWarehouseId() == null){
				result.add(item);
				break;
			}else{
				result.add(item);
				warehouseId = item.getOldWarehouseId();
			}			
		}while(true);
		return result;
	}
	
}
