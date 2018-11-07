package com.zznode.materialmanage.model;

import java.io.Serializable;

/**
 * 品牌
 * @author yanjisheng
 *
 */
public class Brand implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer brandId;
	private String terminalType;
	private String manufacturer;
	private String terminalModel;
	private String property;
	private String networkMode;
	private Byte isRouterSupported;
	private String license;
	private String enterTime;
	private String remark;
	
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTerminalModel() {
		return terminalModel;
	}
	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getNetworkMode() {
		return networkMode;
	}
	public void setNetworkMode(String networkMode) {
		this.networkMode = networkMode;
	}
	public Byte getIsRouterSupported() {
		return isRouterSupported;
	}
	public void setIsRouterSupported(Byte isRouterSupported) {
		this.isRouterSupported = isRouterSupported;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}
