package com.nianhong.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.SubTaskDao;
import com.nianhong.dao.TaskDao;
import com.nianhong.dao.TaskGetDao;
import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.TaskGet;
import com.nianhong.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService{
	@Autowired 
	private TaskGetDao taskGetDao;
	@Autowired
	private SubTaskDao subTaskDao;
	@Autowired
	private TaskDao taskDao;

	@Override
	public Map<String, Object> acceptTask(String accepter, int subTaskID) {
		Map<String, Object> res = new HashMap<String, Object>();
		SubTask subTask = subTaskDao.selectByID(subTaskID);
		TaskGet tg = new TaskGet();
		String taskID = subTaskDao.selectByID(subTaskID).getTask_id();
		tg.setTask_id(taskID);
		tg.setSub_task_id(subTaskID);
		tg.setAccepter(accepter);
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		tg.setAccept_time(date);
		
		//设置任务状态 
		Task task = taskDao.selectTaskByID(taskID);
		//判断任务是否需要被审核（要添加时间限定，10分钟后如何实现自动改变状态为2？）
		if(0 == task.getVerify()) {
			//任务不需要被审核,此时task_get状态为 任务中
			tg.setStatus(2);
		} else {
			//任务需要被审核
			tg.setStatus(1);
		}
		
		//判断该子任务领取人是否已经到达上限
		//获取当前领取该子任务的实际人数
		int actNum = taskGetDao.selectBySubTaskIDAndStatus(subTaskID, 1).size();
		actNum += taskGetDao.selectBySubTaskIDAndStatus(subTaskID, 2).size();
		actNum += taskGetDao.selectBySubTaskIDAndStatus(subTaskID, 3).size();
		actNum += taskGetDao.selectBySubTaskIDAndStatus(subTaskID, 4).size();
		//实际人数已达到设定人数
		if(actNum >= subTask.getPerson_need()) {
			System.out.println("error:actNum="+actNum+" >= subTask.getPerson_need()"+subTask.getPerson_need());
			res.put("success", "false");
			res.put("message", "此任务实际人数已达到设定人数");
			return res;
		}
		
		//判断该用户是否接受过此任务
		int accNum = taskGetDao.selectBySubTaskIDAndAccepter(subTaskID, accepter).size();
		if(accNum > 0) {
			//该用户已接受过此任务
			System.out.println("该用户已接受过此任务+size="+accNum);
			res.put("success", "false");
			res.put("message", "该用户已接受过此任务");
			return res;
		}
		
		if(taskGetDao.insertTaskGet(tg)) {
			res.put("success", "true");
			res.put("message", "插入成功");
		} else {
			res.put("success", "false");
			res.put("message", "插入失败，未知原因...");
		}
		return res;
	}

}
