package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 入库单查询条件
 * @author yanjisheng
 *
 */
public class StockInQuery extends StockIn {
	
	private Date startTime;//入库时间查询起始
	private Date endTime;//入库时间查询截止
	private List<Integer> operatorIdList;//操作人id查询列表
	private List<Integer> warehouseIdList;//仓库id查询列表
	private List<Integer> brandIdList;//品牌id查询列表
	
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
	public List<Integer> getBrandIdList() {
		return brandIdList;
	}
	public void setBrandIdList(List<Integer> brandIdList) {
		this.brandIdList = brandIdList;
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
	public void setWarehouseId(Integer warehouseId) {
		super.setWarehouseId(warehouseId);
		if(warehouseIdList == null){
			warehouseIdList = new ArrayList<>();
		}
		this.warehouseIdList.add(warehouseId);
	}
	@Override
	public void setBrandId(Integer brandId) {
		super.setBrandId(brandId);
		if(brandIdList == null){
			brandIdList = new ArrayList<>();
		}
		this.brandIdList.add(brandId);
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
		
}
