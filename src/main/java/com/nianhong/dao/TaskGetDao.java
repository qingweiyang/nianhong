package com.nianhong.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nianhong.model.TaskGet;

public interface TaskGetDao {
	
	/**
	 * 向task_get表中插入一条数据
	 * 
	 * @param taskGet
	 * @return
	 */
	public boolean insertTaskGet(TaskGet taskGet);

	/**
	 * 根据taskID获取taskGet
	 * 
	 * @param taskID
	 * @return
	 */
	public List<TaskGet> selectByTaskID(String taskID);
	
	public TaskGet selectByID(int id);

	public List<TaskGet> selectBySubTaskID(int subTaskID);

	public List<TaskGet> selectBySubTaskIDAndAccepter(@Param(value="subTaskID")int subTaskID,
			@Param(value="accepter") String accepter);

	public List<TaskGet> selectBySubTaskIDAndStatus(@Param(value="subTaskID")int subTaskID,
			@Param(value="status") int status);

	public List<TaskGet> selectByAccepterAndTaskID(@Param(value="accepter") String accepter,
			@Param(value="taskID") String taskID);

	/**
	 * 统计雇主与卖家在要求的时间段内交易的次数
	 * 
	 * @param publisher
	 * @param accepter
	 * @param startTime
	 * @param finishTime
	 * @return
	 */
	public int selectCountByDealtimeAndStatus(@Param(value="publisher") String publisher, @Param(value="accepter")String accepter,
			@Param(value="status")int status, @Param(value="startTime")Date startTime, @Param(value="finishTime")Date finishTime);
	
	/**
	 * 获取状态为 2，3，4 的任务接受列
	 * 
	 * @param taskID
	 * @return
	 */
	public List<TaskGet> selectWaitAcceptByTaskID(String taskID);
	
	/**
	 * 根据TaskGet的id为主键，更新除ID外的所有属性值
	 * 
	 * @param taskGet
	 * @return
	 */
	public boolean updateTaskGet(TaskGet taskGet);
	
	/**
	 * 更新task_get表的状态值
	 * 
	 * @param taskID
	 * @param username
	 * 		接受任务者名称
	 * @param status
	 * @return 
	 */
	public boolean updateStatusByTaskIDAndAccepter(@Param(value = "taskID")String taskID, 
			@Param(value = "accepter")String username, @Param(value="status") int status);
	
	/**
	 * 更新task_get表的状态值
	 * 
	 * @param taskGetID
	 * @param status
	 * @return 
	 */
	public boolean updateStatusByTaskGetID(@Param(value = "taskGetID")String taskGetID, 
			@Param(value="status") int status);
	
	/**
	 * 更新task_get表的备注值
	 * 
	 * @param taskID
	 * @param username
	 * 		接受任务者名称
	 * @param 
	 * @return
	 */
	public boolean updateRemarkByTaskIDAndAccepter(@Param(value = "taskID")String taskID, 
			@Param(value = "accepter")String username, @Param(value="remark") String remark);
	
	/**
	 * 更新task_get表的备注值
	 * 
	 * @param taskID
	 * @param 
	 * @return
	 */
	public boolean updateRemarkByTaskGetID(@Param(value = "taskGetID")String taskGetID, 
			@Param(value="remark") String remark);
	
}
