package com.nianhong.util;

import com.nianhong.model.User;

public class LoginInf {

	public static String username;
	
	/**
	 * 获取当前登入的用户
	 * 
	 * @return
	 */
	public static User getCurUser() {
		User user = new User();
		user.setUsername("yqwTest");
		user.setVip(1);
		return user;
	}
}
