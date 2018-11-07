package com.zznode.materialmanage.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageSerializable;
import com.zznode.materialmanage.controller.helper.PageQueryModel;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.Warehouse;
import com.zznode.materialmanage.model.WarehouseQuery;
import com.zznode.materialmanage.model.WarehouseStaff;
import com.zznode.materialmanage.service.UserService;
import com.zznode.materialmanage.service.WarehouseService;
import com.zznode.materialmanage.service.WarehouseStaffService;

/**
 * 仓库管理
 * @author yanjisheng
 *
 */
@RestController
public class WarehouseController {
	
	@Autowired
	WarehouseService warehouseService;
	
	@Autowired
	WarehouseStaffService warehouseStaffService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 添加仓库信息
	 */
	@PostMapping("/warehouse/addWarehouse")
	public void addWarehouse(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			@RequestParam String warehouseName, String warehouseType, String provinceCode, String cityCode, @RequestParam String address) 
			throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_MANAGER){
			throw new MaterialManageException("错误：仅限管理员操作");
		}
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseName(warehouseName);
		warehouse.setWarehouseType(warehouseType);
		warehouse.setProvinceCode(provinceCode);
		warehouse.setCityCode(cityCode);
		warehouse.setAddress(address);
		warehouse.setCreateTime(new Date());
		warehouseService.addNew(warehouse);
	}
	
	/**
	 * 添加仓库与员工的对应关系
	 * @throws MaterialManageException 
	 */
	@PostMapping("/warehouse/addWarehouseStaff")
	public void addWarehouseStaff(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			@RequestParam Integer warehouseId, @RequestParam Integer userId, Byte isManager) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_MANAGER){
			throw new MaterialManageException("错误：仅限管理员操作");
		}
		WarehouseStaff warehouseStaff = new WarehouseStaff();
		warehouseStaff.setWarehouseId(warehouseId);
		warehouseStaff.setUserId(userId);
		warehouseStaff.setIsManager(isManager);
		warehouseStaffService.addOne(warehouseStaff);
	}
	
	/**
	 * 分页查询仓库信息
	 */
	@GetMapping("/warehouse/queryWarehouse")
	public PageSerializable<Warehouse> queryWarehouse(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			WarehouseQuery condition, PageQueryModel pageQuery) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return warehouseService.selectByCondition(condition, pageQuery);
	}
	
	/**
	 * 按仓库id查询对应员工信息
	 */
	@GetMapping("/warehouse/getUsersByWarehouseId")
	public List<WarehouseStaff> getUsersByWarehouseId(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			Integer warehouseId) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return warehouseStaffService.selectByWarehouseId(warehouseId);
	}
	
	/**
	 * 按员工id查询对应仓库信息
	 */
	@GetMapping("/warehouse/getWarehouseByUserId")
	public List<WarehouseStaff> getWarehouseByUserId(@RequestHeader(required=false) String loginName, @RequestHeader(required=false) String tokenString, 
			Integer userId) throws MaterialManageException{
		if(userService.getUserPrivilege(loginName, tokenString) < UserService.PRIVILEGE_STAFF){
			throw new MaterialManageException("错误：仅限在职员工操作");
		}
		return warehouseStaffService.selectByUserId(userId);
	}
	
}
