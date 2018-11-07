package com.zznode.materialmanage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author yanjisheng
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String loginName;
	private String password;//加密前的密码
	private String hashPassword;
	private String name;
	private String staffNo;
	private String phone;
	private Date employDate;
	private Byte status;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
}
