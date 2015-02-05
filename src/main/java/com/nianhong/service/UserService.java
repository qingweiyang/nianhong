package com.nianhong.service;

import com.nianhong.model.User;


public interface UserService {

	/**
	 * 根据用户名和密码选择用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User selectByUsernameAndPassword(String username, String password);

	
}
