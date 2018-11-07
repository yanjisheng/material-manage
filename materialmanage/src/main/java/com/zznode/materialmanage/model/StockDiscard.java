package com.zznode.materialmanage.model;

import java.util.Date;

/**
 * 报废单
 * @author yanjisheng
 *
 */
public class StockDiscard {
	
	private Integer stockDiscardId;
	private String terminalSn;
	private Integer operatorId;
	private User operator;//操作人（关联查询）
	private Date operateTime;
	private String remark;
	
	public Integer getStockDiscardId() {
		return stockDiscardId;
	}
	public void setStockDiscardId(Integer stockDiscardId) {
		this.stockDiscardId = stockDiscardId;
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
	
}
