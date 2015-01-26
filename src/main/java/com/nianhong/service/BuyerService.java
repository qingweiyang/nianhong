package com.nianhong.service;

import java.util.Map;

public interface BuyerService {

	/**
	 * 买家接受任务
	 * 
	 * @param accepter
	 * @param subTaskID
	 * @return
	 * 		失败返回失败原因{"success":"false","message":"任务已被抢订"}
	 */
	public Map<String, Object> acceptTask(String accepter, int subTaskID);
}
