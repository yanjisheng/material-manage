package com.zznode.materialmanage.model;

import java.util.Date;

/**
 * 回收单
 * @author yanjisheng
 *
 */
public class StockRecycle {
	
	private Integer stockRecycleId;
	private Integer operatorId;
	private User operator;//操作人员详细信息
	private Integer installerId;
	private User installer;//装维人员详细信息
	private String terminalSn;
	private Date operateTime;
	private String customerAccount;
	private Date recycleTime;
	private Integer warehouseId;
	private Warehouse warehouse;//仓库详细信息
	private String remark;
	
	public Integer getStockRecycleId() {
		return stockRecycleId;
	}
	public void setStockRecycleId(Integer stockRecycleId) {
		this.stockRecycleId = stockRecycleId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
	public String getTerminalSn() {
		return terminalSn;
	}
	public void setTerminalSn(String terminalSn) {
		this.terminalSn = terminalSn;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public Date getRecycleTime() {
		return recycleTime;
	}
	public void setRecycleTime(Date recycleTime) {
		this.recycleTime = recycleTime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getInstallerId() {
		return installerId;
	}
	public void setInstallerId(Integer installerId) {
		this.installerId = installerId;
	}
	public User getInstaller() {
		return installer;
	}
	public void setInstaller(User installer) {
		this.installer = installer;
	}

}
