package com.zznode.materialmanage.model;

import java.util.Date;
import java.util.List;

/**
 * 入库单
 * @author yanjisheng
 *
 */
public class StockIn {

	private Integer id;
	private Integer stockInId;
	private Integer operatorId;
	private User operator;//操作人信息
	private Date operateTime;
	private Integer warehouseId;
	private Warehouse warehouse;//仓库信息
	private Integer brandId;
	private Brand brand;//品牌信息
	private String remark;
	private List<String> snList;//入库单终端sn列表
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStockInId() {
		return stockInId;
	}
	public void setStockInId(Integer stockInId) {
		this.stockInId = stockInId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public List<String> getSnList() {
		return snList;
	}
	public void setSnList(List<String> snList) {
		this.snList = snList;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
