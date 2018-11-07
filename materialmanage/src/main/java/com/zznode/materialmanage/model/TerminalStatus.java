package com.zznode.materialmanage.model;

import java.io.Serializable;

/**
 * 终端状态
 * @author yanjisheng
 *
 */
public class TerminalStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Short statusCode;
	private String statusName;
	
	public Short getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Short statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}	
}
