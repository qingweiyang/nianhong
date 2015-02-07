package com.nianhong.dao;

import org.apache.ibatis.annotations.Param;

import com.nianhong.model.Integral;

public interface IntegralDao {
	
	/**
	 * 根据用户名选择用户的积分信息
	 * 
	 * @param username
	 * @return
	 */
	public Integral selectByUsername(String username);
	
	public boolean updateValueAndFreezeByUsername(@Param(value="username") String username, 
			@Param(value="value") double value, @Param(value="freeze") double freeze);
	
	public boolean updateIntegral(Integral integral);
}
