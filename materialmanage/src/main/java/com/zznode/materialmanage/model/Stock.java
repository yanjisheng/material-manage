package com.zznode.materialmanage.model;

/**
 * 终端库存
 * @author yanjisheng
 *
 */
public class Stock {

	private Integer terminalId;
	private String terminalSn;
	private Integer brandId;
	private Brand brand;//品牌信息
	private Integer warehouseId;
	private Warehouse warehouse;//仓库信息
	private Short status;
	private String statusName;//终端状态名称
	
	public Integer getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalSn() {
		return terminalSn;
	}
	public void setTerminalSn(String terminalSn) {
		this.terminalSn = terminalSn;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
