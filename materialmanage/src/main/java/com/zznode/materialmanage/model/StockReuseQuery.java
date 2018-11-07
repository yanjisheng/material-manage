package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 备件查询条件
 * @author yanjisheng
 *
 */
public class StockReuseQuery extends StockReuse {

	private Date startTime;
	private Date endTime;
	private List<Integer> operatorIdList;
	private List<Integer> warehouseIdList;
	
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
	public List<Integer> getWarehouseIdList() {
		return warehouseIdList;
	}
	public void setWarehouseIdList(List<Integer> warehouseIdList) {
		this.warehouseIdList = warehouseIdList;
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
	public void setWarehouseId(Integer warehouseId) {
		super.setWarehouseId(warehouseId);
		if(warehouseIdList == null){
			warehouseIdList = new ArrayList<>();
		}
		this.warehouseIdList.add(warehouseId);
	}
	
}
