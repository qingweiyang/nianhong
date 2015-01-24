package com.nianhong.service;

import java.util.HashMap;
import java.util.List;

import com.nianhong.model.SalerTaskDoing;
import com.nianhong.model.Task;
import com.nianhong.model.WaitVerify;
import com.nianhong.vo.TaskVO;

public interface SalerService {

	/**
	 * 雇主发布一条任务
	 * 
	 * @param user
	 * @param task
	 * @return
	 */
	public boolean publishTask(String user, TaskVO task);
	
	/**
	 * 选择所有任务类型
	 * 
	 * @return
	 */
	public List<String> loadType();
	
	/**
	 * 根据权限选择用户相应的任务类型
	 * 	普通用户（0）、高级会员（1）
	 * 
	 * @param pri
	 * @return
	 */
	public List<String> selectTypeByPrivilege(int pri);
	
	/**
	 * 根据任务状态获取雇主待领取的任务信息
	 * 
	 * @param status
	 * @return
	 */
	public List<Task> getSalerTaskByStatus(int status);
	
	/**
	 * 根据任务id删除一条任务纪录（仅删除task表中数据）
	 * 
	 * @param id
	 * @return
	 */
	public int deletetTaskByID(String id);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<Task> selectByPublisherAndStatus(String publisher, int status);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 	任务格式与 我是雇主 我的任务 待领取 一致
	 * loadDoingTask
	 * @param username
	 * @param status
	 * @return
	 */
	public List<WaitVerify> selectWaitVerifyTask(String username);

	/**
	 * 根据雇主名称与状态选择任务
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<HashMap<String, Object>> selectSalerTask(String username, int status);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 	任务格式与 我是雇主 我的任务 任务中 一致
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<SalerTaskDoing> selectDoingTaskModel(String username);
	
	/**
	 * 雇主同意买家领取任务
	 * 
	 * @param taskID
	 * @param accepter
	 * @return
	 */
	public boolean agreeAccepter(String taskID, String accepter);
	
	/**
	 * 雇主拒绝买家领取任务
	 * 
	 * @param taskID
	 * @param accepter
	 * @return
	 */
	public boolean refuseAccepter(String taskID, String accepter);
	
	/**
	 * 雇主中止交易
	 * 
	 * @param taskGetID
	 * @param accepter
	 * @return
	 */
	public boolean suspendDeal(String taskGetID);
	
	/**
	 * 雇主确认交易完成
	 * 
	 * @param taskGetID
	 * @param accepter
	 * @return
	 */
	public boolean sureDeal(String taskGetID);
}
