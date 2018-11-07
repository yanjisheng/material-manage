package com.zznode.materialmanage.service;

import com.zznode.materialmanage.controller.helper.UserTokenHelper;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.User;

/**
 * 用户
 * @author yanjisheng
 *
 */
public interface UserService {
	
	/**
	 * 权限控制：仅限管理员操作
	 */
	public static final int PRIVILEGE_MANAGER = 2;
	
	/**
	 * 权限控制：仅限在职员工操作
	 */
	public static final int PRIVILEGE_STAFF = 1;
	
	/**
	 * 权限控制：仅限登录用户操作
	 */
	public static final int PRIVILEGE_LOGIN = 0;

	/**
	 * 新用户注册
	 */
	public void register(User user) throws MaterialManageException;
	
	/**
	 * 用户登录
	 */
	public UserTokenHelper login(User user) throws MaterialManageException;
	
	/**
	 * 用户登录（含有用户ip参数）<br>
	 * 暂不使用，因为实际情况下很多都是动态ip
	 */
	public UserTokenHelper login(User user, String remoteHost) throws MaterialManageException;
	
	/**
	 * 用户修改密码
	 */
	public void changePassword(User user) throws MaterialManageException;

	/**
	 * 获取用户权限（即user.status）
	 */
	public Byte getUserPrivilege(String loginName, String tokenString) throws MaterialManageException;
	
	/**
	 * 根据id获取用户信息
	 */
	public User getUserById(int userId);
	
	/**
	 * 根据登录名获取用户信息
	 */
	public User getUserByLoginName(String loginName);
}