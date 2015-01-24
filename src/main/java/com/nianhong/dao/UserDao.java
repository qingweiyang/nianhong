package com.nianhong.dao;

import org.apache.ibatis.annotations.Param;

import com.nianhong.model.User;


public interface UserDao {

	/**
	 * 根据用户名和密码选择用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User selectByUsernameAndPassword(@Param(value = "username")String username, @Param(value = "password")String password);
	
	
}
