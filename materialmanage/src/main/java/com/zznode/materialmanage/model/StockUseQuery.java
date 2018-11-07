package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 领用单查询条件
 * @author yanjisheng
 *
 */
public class StockUseQuery extends StockUse{

	private Date startTime;//领用时间查询起始
	private Date endTime;//领用时间查询终止
	private List<Integer> operatorIdList;//操作人id查询列表
	
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
	
}
