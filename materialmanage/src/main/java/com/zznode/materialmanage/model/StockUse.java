package com.zznode.materialmanage.model;

import java.util.Date;

/**
 * 领用单
 * @author yanjisheng
 *
 */
public class StockUse {
	
	private Integer stockUseId;
	private Integer operatorId;
	private User operator;//操作人员详细信息
	private Integer installerId;
	private User installer;//装维人员详细信息
	private String terminalSn;
	private Date operateTime;
	private String customerAccount;
	private Date installTime;
	private String remark;
	
	public Integer getStockUseId() {
		return stockUseId;
	}
	public void setStockUseId(Integer stockUseId) {
		this.stockUseId = stockUseId;
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
	public String getTerminalSn() {
		return terminalSn;
	}
	public void setTerminalSn(String terminalSn) {
		this.terminalSn = terminalSn;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public Date getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getInstallerId() {
		return installerId;
	}
	public void setInstallerId(Integer installerId) {
		this.installerId = installerId;
	}
	public User getInstaller() {
		return installer;
	}
	public void setInstaller(User installer) {
		this.installer = installer;
	}
	
}
