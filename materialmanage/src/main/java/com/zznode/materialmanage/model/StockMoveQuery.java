package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 调拨单查询条件
 * @author yanjisheng
 *
 */
public class StockMoveQuery extends StockMove {
	
	private Date startTime;//操作时间查询起始
	private Date endTime;//操作时间查询截止
	private List<Integer> operatorIdList;//操作人id查询列表
	private List<Integer> warehouseInIdList;//入库仓库id查询列表
	private List<Integer> warehouseOutIdList;//出库仓库id查询列表
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<Integer> getOperatorIdList() {
		return operatorIdList;
	}
	public void setOperatorIdList(List<Integer> operatorIdList) {
		this.operatorIdList = operatorIdList;
	}
	public List<Integer> getWarehouseInIdList() {
		return warehouseInIdList;
	}
	public void setWarehouseInIdList(List<Integer> warehouseInIdList) {
		this.warehouseInIdList = warehouseInIdList;
	}
	public List<Integer> getWarehouseOutIdList() {
		return warehouseOutIdList;
	}
	public void setWarehouseOutIdList(List<Integer> warehouseOutIdList) {
		this.warehouseOutIdList = warehouseOutIdList;
	}
	
	@Override
	public void setOperatorId(Integer operatorId) {
		super.setOperatorId(operatorId);
		if(operatorIdList == null){
			operatorIdList = new ArrayList<>();
		}
		this.operatorIdList.add(operatorId);
	}
	@Override
	public void setOperateTime(Date operateTime) {
		super.setOperateTime(operateTime);
		if(operateTime == null){
			startTime = null;
			endTime = null;
		}
		if(startTime == null || operateTime.before(startTime)){
			startTime = operateTime;
		}
		if(endTime == null || operateTime.after(endTime)){
			endTime = operateTime;
		}
	}
	@Override
	public void setWarehouseInId(Integer warehouseInId) {
		super.setWarehouseInId(warehouseInId);
		if(warehouseInIdList == null){
			warehouseInIdList = new ArrayList<>();
		}
		this.warehouseInIdList.add(warehouseInId);
	}
	@Override
	public void setWarehouseOutId(Integer warehouseOutId) {
		super.setWarehouseOutId(warehouseOutId);
		if(warehouseOutIdList == null){
			warehouseOutIdList = new ArrayList<>();
		}
		this.warehouseOutIdList.add(warehouseOutId);
	}
	
}
