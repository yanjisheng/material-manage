package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 终端库存查询条件
 * @author yanjisheng
 *
 */
public class StockQuery extends Stock {
	
	private List<Integer> brandIdList;//品牌编号查询条件列表
	private List<Integer> warehouseIdList;//终端状态查询条件列表
	private List<Short> statusList;//终端状态列表
	
	public List<Integer> getBrandIdList() {
		return brandIdList;
	}
	public void setBrandIdList(List<Integer> brandIdList) {
		this.brandIdList = brandIdList;
	}
	public List<Integer> getWarehouseIdList() {
		return warehouseIdList;
	}
	public void setWarehouseIdList(List<Integer> warehouseIdList) {
		this.warehouseIdList = warehouseIdList;
	}
	public List<Short> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Short> statusList) {
		this.statusList = statusList;
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
	public void setWarehouseId(Integer warehouseId) {
		super.setWarehouseId(warehouseId);
		if(warehouseIdList == null){
			warehouseIdList = new ArrayList<>();
		}
		this.warehouseIdList.add(warehouseId);
	}
	@Override
	public void setStatus(Short status) {
		super.setStatus(status);
		if(statusList == null){
			statusList = new ArrayList<>();
		}
		this.statusList.add(status);
	}
	
	
}
