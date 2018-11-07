package com.zznode.materialmanage.model;

import java.util.Date;

/**
 * 仓库查询条件
 * @author yanjisheng
 *
 */
public class WarehouseQuery extends Warehouse {

	private static final long serialVersionUID = 1L;
	
	private Date startTime;//创建时间开始
	private Date endTime;//创建时间截止
	
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
	
	@Override
	public void setCreateTime(Date createTime) {
		super.setCreateTime(createTime);
		if(createTime == null){
			startTime = null;
			endTime = null;
		}
		if(startTime == null || createTime.before(startTime)){
			startTime = createTime;
		}
		if(endTime == null || createTime.after(endTime)){
			endTime = createTime;
		}
	}
}
