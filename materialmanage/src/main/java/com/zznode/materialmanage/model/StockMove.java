package com.zznode.materialmanage.model;

import java.util.Date;
import java.util.List;

/**
 * 调拨单
 * @author yanjisheng
 *
 */
public class StockMove {

	private Integer id;
	private Integer stockMoveId;
	private Integer operatorId;
	private User operator;//操作人信息
	private Date operateTime;
	private Integer warehouseInId;
	private Warehouse warehouseIn;//调入仓库信息
	private Integer warehouseOutId;
	private Warehouse warehouseOut;//调出仓库信息
	private String remark;
	private List<String> snList;//调拨单终端sn列表
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStockMoveId() {
		return stockMoveId;
	}
	public void setStockMoveId(Integer stockMoveId) {
		this.stockMoveId = stockMoveId;
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
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getWarehouseInId() {
		return warehouseInId;
	}
	public void setWarehouseInId(Integer warehouseInId) {
		this.warehouseInId = warehouseInId;
	}
	public Warehouse getWarehouseIn() {
		return warehouseIn;
	}
	public void setWarehouseIn(Warehouse warehouseIn) {
		this.warehouseIn = warehouseIn;
	}
	public Integer getWarehouseOutId() {
		return warehouseOutId;
	}
	public void setWarehouseOutId(Integer warehouseOutId) {
		this.warehouseOutId = warehouseOutId;
	}
	public Warehouse getWarehouseOut() {
		return warehouseOut;
	}
	public void setWarehouseOut(Warehouse warehouseOut) {
		this.warehouseOut = warehouseOut;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<String> getSnList() {
		return snList;
	}
	public void setSnList(List<String> snList) {
		this.snList = snList;
	}
	
}
