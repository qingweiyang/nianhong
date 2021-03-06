package com.nianhong.dao;

import java.util.List;

import com.nianhong.model.SubTask;

public interface SubTaskDao {

	/**
	 *  新增一条子任务
	 * 
	 * @param subTask
	 * @return
	 */
	public int insertSubTask(SubTask subTask);
	
	/**
	 * 删除taskid对应任务的所有子任务
	 * @param taskID
	 * @return
	 */
	public int deleteSubTaskByID(String taskID);
	
	public List<SubTask> selectSubTaskByTaskID(String taskID);

	public SubTask selectByID(int id);

}
