package com.nianhong.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nianhong.model.SalerTaskDoing;
import com.nianhong.model.Task;
import com.nianhong.model.WaitVerify;

public interface TaskDao {
	
	/**
	 * 新增一条任务
	 * 
	 * @param task
	 * @return
	 */
	public int insertTask(Task task);
	
	/**
	 * 根据任务类型选择所有任务（会员专享、平面设计...）
	 * 
	 * @param type
	 * @return
	 */
	public List<Task> selectByType(String type);
	
	/**
	 * 根据id获取任务
	 * 
	 * @param id
	 * @return
	 */
	public Task selectTaskByID(String id);
	
	/**
	 * 根据任务状态（已删除（0）、未删除（1））和类型选择所有任务
	 * 
	 * @param status
	 * @param type
	 * @return
	 */
	public List<Task> selectByStatusAndType(@Param(value = "status") int status, @Param(value = "type")String type);
	
	/**
	 * 根据状态选择任务
	 * 
	 * @return
	 */
	public List<Task> selectByStatus(int status);
	
	/**
	 * 根据任务发布者选择该发布者发布的所有任务
	 * 
	 * @param publisher
	 * @return
	 */
	public List<Task> selectByPublisher(String publisher);
	
	/**
	 * 根据任务id删除一条任务纪录（将任务状态标记为删除）
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
	public List<Task> selectByPublisherAndStatus(@Param(value="publisher") String publisher, @Param(value="status") int status);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 	任务格式与 我是雇主 我的任务 待领取 一致
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<WaitVerify> selectWaitVerifyModel(@Param(value="publisher") String publisher, @Param(value="status") int status);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 	任务格式与 我是雇主 我的任务 任务中 一致
	 * 
	 * @param username
	 * @param status
	 * @return
	 */
	public List<SalerTaskDoing> selectTaskDoingModel(@Param(value="publisher") String publisher, @Param(value="status1") int status1, @Param(value="status2") int status2);
	
	/**
	 * 根据雇主名称与状态选择任务
	 * 
	 * @param publisher
	 * @param status
	 * @return
	 */
	public List<HashMap<String, Object>> selectSalerTask(@Param(value="publisher") String publisher, @Param(value="status") int status);

	/**
	 * 根据接受者（买家）名称与状态选择任务
	 * 
	 * @param accepter
	 * @param status
	 * @return
	 */
	public List<HashMap<String, Object>> selectBuyerTask(@Param(value="accepter") String accepter, @Param(value="status") int status);

	/**
	 * 选择雇主与买家的交易记录
	 * 
	 * @param publisher
	 * @param accepter
	 * @return
	 */
	public List<Map<String, Object>> selectDeal(@Param(value="publisher") String publisher, @Param(value="accepter") String accepter);
}
