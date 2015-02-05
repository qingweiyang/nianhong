package com.nianhong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nianhong.model.IntegralLog;

public interface IntegralLogDao {
	
	/**
	 * 插入一条积分日志数据
	 * 
	 * @param integral
	 * @return
	 */
	public boolean insertIntegralLog(IntegralLog integralLog);
	
	/**
	 * 根据指定参数获取积分日志
	 * 
	 * @param initiator
	 * @param type
	 * @param status
	 * @return
	 */
	public List<IntegralLog> selectByInitiatorAndTypeAndStatus(@Param(value="initiator") String initiator,
			@Param(value="type") int type, @Param(value="status") int status);
}
