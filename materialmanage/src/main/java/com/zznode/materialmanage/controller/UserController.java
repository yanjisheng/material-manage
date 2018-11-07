package com.zznode.materialmanage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zznode.materialmanage.controller.helper.UserTokenHelper;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.User;
import com.zznode.materialmanage.service.UserService;
import com.zznode.materialmanage.util.UserToken;

/**
 * 用户管理
 * @author yanjisheng
 *
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserToken userToken;
	
	/**
	 * 注册
	 */
	@PostMapping("/user/register")
	public void register(@RequestParam String loginName, @RequestParam String password, 
			String name, String staffNo, String phone, Date employDate) throws MaterialManageException{
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setName(name);
		user.setStaffNo(staffNo);
		user.setPhone(phone);
		if(employDate != null){
			user.setEmployDate(employDate);
		}else{
			user.setEmployDate(new Date());
		}
		userService.register(user);
	}
	
	/**
	 * 登录
	 */
	@PostMapping("/user/login")
	public UserTokenHelper login(@RequestParam String loginName, @RequestParam String password) throws MaterialManageException{
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		UserTokenHelper token = null;
		token = userService.login(user);
		return token;
	}
	
	/**
	 * 修改密码
	 */
	@PostMapping("/user/changePassword")
	public void changePassword(@RequestHeader String loginName, @RequestHeader String tokenString, 
			@RequestParam String password) throws MaterialManageException{
		if(userToken.authorizeToken(loginName, tokenString) == false){
			throw new MaterialManageException("错误：用户token异常");
		}
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		userService.changePassword(user);
	}
}
