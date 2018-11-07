package com.zznode.materialmanage.model;

/**
 * 仓库与员工对应关系
 * @author yanjisheng
 *
 */
public class WarehouseStaff {
	
	private Integer id;
	private Integer warehouseId;
	private Warehouse warehouse;//关联仓库信息
	private Integer userId;
	private User user;//关联用户信息
	private Byte isManager;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Byte getIsManager() {
		return isManager;
	}
	public void setIsManager(Byte isManager) {
		this.isManager = isManager;
	}
	
}
