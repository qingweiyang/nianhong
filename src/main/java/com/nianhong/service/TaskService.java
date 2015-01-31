package com.nianhong.service;

import java.util.List;

import com.nianhong.model.Task;
import com.nianhong.vo.SalerInfVO;
import com.nianhong.vo.SubTaskVO;
import com.nianhong.vo.TaskVO;

/**
 * 任务大厅 的所有服务
 * 
 * @author yqw
 *
 */
public interface TaskService {
	
	/**
	 * 获取所有类型为type的任务(已完成和删除的任务除外)，任务按照发布时间顺序排列（最近发布的优先）
	 * 
	 * @param type
	 * @return
	 */
	public List<Task> getTimeSortedTaskByType(String type);
	
	/**
	 * 根据任务id获取任务（包括子任务信息）
	 * 
	 * @param taskID
	 * @return
	 */
	public Task getTaskByID(String taskID);
	
	/**
	 * 获取卖家基本信息，信息要求对应salerInfVO
	 * 
	 * @param username
	 * @return
	 */
	public SalerInfVO getSalerInf(String username);
	
	/**
	 * 根据任务id获取任务信息，信息格式为TaskVO
	 * 
	 * @param taskID
	 * @return
	 */
	public TaskVO getTaskVOByID(String accepter, String taskID);

	/**
	 * 根据任务subtaskid获取任务信息，信息格式为subtaskVO
	 * 
	 * @param accepter
	 * @param taskID
	 * @return
	 */
	public SubTaskVO getTaskVOBySubtaskID(String accepter, int subtaskid);
}
