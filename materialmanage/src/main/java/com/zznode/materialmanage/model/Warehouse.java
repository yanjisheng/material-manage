package com.zznode.materialmanage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库
 * @author yanjisheng
 *
 */
public class Warehouse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer warehouseId;
	private Integer oldWarehouseId;
	private String warehouseName;
	private String warehouseType;
	private String provinceCode;
	private String cityCode;
	private String address;
	private Byte status;
	private Date createTime;
	
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getOldWarehouseId() {
		return oldWarehouseId;
	}
	public void setOldWarehouseId(Integer oldWarehouseId) {
		this.oldWarehouseId = oldWarehouseId;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
}
