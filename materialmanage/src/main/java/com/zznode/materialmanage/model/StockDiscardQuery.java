package com.zznode.materialmanage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报废单查询条件
 * @author yanjisheng
 *
 */
public class StockDiscardQuery extends StockDiscard {

	private Date startTime;
	private Date endTime;
	private List<Integer> operatorIdList;
	
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
