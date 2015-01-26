package com.nianhong.dao;

import java.util.List;

public interface TaskTypeDao {
	
	/**
	 * 根据权限选择用户相应的任务类型
	 * 	普通用户（0）、高级会员（1）
	 * 	选取规则：权限高的获取所有低于该权限的任务类型（1拥有0，1的权限）
	 * 
	 * @param pri
	 * @return
	 */
	public List<String> selectTypeByPrivilege(int pri);
	
	/**
	 * 选择所有任务类型
	 * 
	 * @return
	 */
	public List<String> selectAllType();
	
}
