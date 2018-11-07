package com.zznode.materialmanage.model;

import java.util.Date;

/**
 * 备件单
 * @author yanjisheng
 *
 */
public class StockReuse {
	
	private Integer stockReuseId;
	private String terminalSn;
	private Integer operatorId;
	private User operator;//操作人（关联查询）
	private Date operateTime;
	private Integer warehouseId;
	private Warehouse warehouse;//仓库信息（关联查询）
	private String remark;
	
	public Integer getStockReuseId() {
		return stockReuseId;
	}
	public void setStockReuseId(Integer stockReuseId) {
		this.stockReuseId = stockReuseId;
	}
	public String getTerminalSn() {
		return terminalSn;
	}
	public void setTerminalSn(String terminalSn) {
		this.terminalSn = terminalSn;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
}
