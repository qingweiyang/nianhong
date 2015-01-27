package com.nianhong.service;

import java.util.HashMap;
import java.util.List;
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
	
	/**
	 * 根据买家名称与状态选择任务
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<HashMap<String, Object>> selectBuyerTask(String username, int status);
}
