package com.zznode.materialmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.dao.WarehouseStaffDao;
import com.zznode.materialmanage.model.WarehouseStaff;
import com.zznode.materialmanage.service.WarehouseStaffService;

/**
 * 仓库与员工对应关系
 * @author yanjisheng
 *
 */
@Service
public class WarehouseStaffServiceImpl implements WarehouseStaffService {

	private static Logger log = LoggerFactory.getLogger(WarehouseStaffServiceImpl.class);
	
	@Autowired
	WarehouseStaffDao dao;
	
	@Override
	public void addOne(WarehouseStaff warehouseStaff) {
		log.info("添加员工["+warehouseStaff.getUserId()+"]与仓库["+warehouseStaff.getWarehouseId()+"]的对应关系");
		dao.addOne(warehouseStaff);
	}

	@Override
	public WarehouseStaff selectById(int id) {
		log.info("按id["+id+"]查询仓库与员工对应关系");
		return dao.selectById(id);
	}

	@Override
	public List<WarehouseStaff> selectByWarehouseId(int warehouseId) {
		log.info("查询仓库["+warehouseId+"]对应的员工");
		WarehouseStaff condition = new WarehouseStaff();
		condition.setWarehouseId(warehouseId);
		return dao.selectByCondition(condition);
	}

	@Override
	public List<WarehouseStaff> selectByUserId(int userId) {
		log.info("查询员工["+userId+"]对应的仓库");
		WarehouseStaff condition = new WarehouseStaff();
		condition.setUserId(userId);
		return dao.selectByCondition(condition);
	}

	@Override
	public PageSerializable<WarehouseStaff> selectManager(PageQueryModel pageQuery) {
		log.info("分页查询各仓库的管理员");
		WarehouseStaff condition = new WarehouseStaff();
		condition.setIsManager((byte)1);
		PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getOrderBy());
		List<WarehouseStaff> list = dao.selectByCondition(condition);
		return new PageSerializable<>(list);
	}

	@Override
	public void deleteById(int id) {
		log.info("按id["+id+"]删除仓库与员工对应关系");
		dao.deleteById(id);
	}

}
