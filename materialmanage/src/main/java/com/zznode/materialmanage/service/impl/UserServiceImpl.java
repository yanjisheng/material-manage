package com.zznode.materialmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zznode.materialmanage.controller.helper.UserTokenHelper;
import com.zznode.materialmanage.dao.UserDao;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.model.User;
import com.zznode.materialmanage.service.UserService;
import com.zznode.materialmanage.util.Md5Password;
import com.zznode.materialmanage.util.PropertyReader;
import com.zznode.materialmanage.util.UserToken;

/**
 * 用户管理
 * @author yanjisheng
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao dao;
	
	@Autowired
	UserToken userToken;
	
	@Autowired
	PropertyReader property;

	@Override
	public void register(User user) throws MaterialManageException {
		log.info("用户["+user.getLoginName()+"]注册");
		User userInDatabase = dao.selectByLoginName(user.getLoginName());
		if(userInDatabase != null){
			throw new MaterialManageException("错误：用户名已存在");
		}
		user.setHashPassword(Md5Password.toMd5(user.getPassword(), user.getLoginName()));
		dao.addOne(user);
	}

	@Override
	public UserTokenHelper login(User user) throws MaterialManageException {
		log.info("用户["+user.getLoginName()+"]登录");
		User userInDatabase = dao.selectByLoginName(user.getLoginName());
		if(userInDatabase == null){
			throw new MaterialManageException("错误：用户名不存在");
		}
		if(!Md5Password.toMd5(user.getPassword(), user.getLoginName())
				.equals(dao.getPasswordById(userInDatabase.getUserId()))){
			throw new MaterialManageException("错误：密码错误");
		}
		return userToken.generateToken(user);
	}
	
	@Override
	public UserTokenHelper login(User user, String remoteHost) throws MaterialManageException {
		log.info("用户["+user.getLoginName()+"]登录");
		User userInDatabase = dao.selectByLoginName(user.getLoginName());
		if(userInDatabase == null){
			throw new MaterialManageException("错误：用户名不存在");
		}
		if(!Md5Password.toMd5(user.getPassword(), user.getLoginName())
				.equals(dao.getPasswordById(userInDatabase.getUserId()))){
			throw new MaterialManageException("错误：密码错误");
		}
		return userToken.generateToken(user);
	}

	@Override
	public void changePassword(User user) throws MaterialManageException{
		log.info("用户["+user.getLoginName()+"]修改密码");
		User userInDatabase = dao.selectByLoginName(user.getLoginName());
		if(userInDatabase == null){
			throw new MaterialManageException("错误：用户名不存在");
		}
		userInDatabase.setHashPassword(Md5Password.toMd5(user.getPassword(), user.getLoginName()));
		dao.changePassword(userInDatabase);
	}
	
	@Override
	public Byte getUserPrivilege(String loginName, String tokenString) throws MaterialManageException{
		log.info("用户["+loginName+"]获取权限");
		if(!property.isEnableTokenAuth()){
			return 2;
		}
		userToken.authorizeToken(loginName, tokenString);
		User user = dao.selectByLoginName(loginName);
		if(user != null){
			return user.getStatus();
		}		
		return 0;
	}

	@Override
	public User getUserById(int userId){
		log.info("根据id["+userId+"]获取用户详细信息");
		return dao.selectById(userId);
	}
	
	@Override
	public User getUserByLoginName(String loginName) {
		log.info("根据登录名["+loginName+"]获取用户信息");
		return dao.selectByLoginName(loginName);
	}
	
}
