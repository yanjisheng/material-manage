package com.zznode.materialmanage.controller.helper;

import java.util.Date;

/**
 * 用户token验证
 * @author yanjisheng
 *
 */
public class UserTokenHelper {
	
	private String loginName;//登录名
	private String tokenString;//token字符串
	private Date availableUntil;//token有效期
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTokenString() {
		return tokenString;
	}
	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	public Date getAvailableUntil() {
		return availableUntil;
	}
	public void setAvailableUntil(Date availableUntil) {
		this.availableUntil = availableUntil;
	}
	
}
