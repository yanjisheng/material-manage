package com.zznode.materialmanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.zznode.materialmanage.model.User;

/**
 * 用户
 * @author yanjisheng
 *
 */
@Mapper
public interface UserDao {

	public User selectById(@Param("userId") int userId);
	
	public User selectByLoginName(@Param("loginName") String loginName);
	
	public String getPasswordById(@Param("userId") int userId);
		
	public int addOne(User user);
	
	public int deleteById(@Param("userId") int userId);
	
	public int changePassword(User user);
	
	public int updateInfo(User user);
}
