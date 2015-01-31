package com.nianhong.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.IntegralDao;
import com.nianhong.dao.SubTaskDao;
import com.nianhong.dao.TaskDao;
import com.nianhong.dao.TaskGetDao;
import com.nianhong.model.Integral;
import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.TaskGet;
import com.nianhong.service.BuyerService;
import com.nianhong.util.LoginInf;

@Service
public class BuyerServiceImpl implements BuyerService{
	@Autowired 
	private TaskGetDao taskGetDao;
	@Autowired
	private SubTaskDao subTaskDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private IntegralDao integralDao;

	@Override
	public Map<String, Object> acceptTask(String accepter, int subTaskID) {
		//accepter被认定为登入用户
		accepter = LoginInf.username;
				
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
		
		Task task = taskDao.selectTaskByID(taskID);
		//设置任务状态 
		//判断任务是否需要被审核（要添加时间限定，10分钟后如何实现自动改变状态为2？）
		if(0 == task.getVerify()) {
			//任务不需要被审核,此时task_get状态为 任务中
			tg.setStatus(2);
		} else {
			//任务需要被审核(这里要添加10分钟后自动改变状态为2....)
			tg.setStatus(1);
		}
		
		//根据任务要求［买家垫付金额］判断用户是否能接该任务,成功则冻结要求积分
		//该任务需要冻结积分
		double integralFreeze = task.getBuyer_freeze();
		Integral integral = integralDao.selectByUsername(accepter);
		double integralOwn = integral.getValue();
		if(integralFreeze > integralOwn) {
			//雇主要求冻结积分已大于买家可预支积分上限
			System.out.println("雇主要求冻结积分已大于买家可预支积分上限 integralFreeze="+integralFreeze+",integralOwn="+integralOwn);
			res.put("success", "false");
			res.put("message", "雇主要求冻结积分已大于买家可预支积分上限");
			return res;
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
		
		//开始向数据库中更新数据
		//冻结积分操作
		double accepterFreeze = integral.getFreeze();
		boolean succes = integralDao.updateValueAndFreezeByUsername(accepter, integralOwn-integralFreeze, integralFreeze+accepterFreeze);
		if(!succes) {
			res.put("success", "false");
			res.put("message", "更新integral表失败，未知原因...");
			return res;
		}
		
		if(taskGetDao.insertTaskGet(tg)) {
			res.put("success", "true");
			res.put("message", "插入成功");
		} else {
			res.put("success", "false");
			res.put("message", "插入task_get表失败，未知原因...");
		}
		return res;
	}

	@Override
	public List<HashMap<String, Object>> selectBuyerTask(String username,
			int status) {
		return taskDao.selectBuyerTask(username, status);
	}

	@Override
	public boolean submitTask(String username, int subTaskID, String describe, String picAddress, int personInf, String others) {
		//因为一个用户只能接收同一个任务一次
		TaskGet taskGet = taskGetDao.selectBySubTaskIDAndAccepter(subTaskID, username).get(0);
		System.out.println(taskGet.getId());
		taskGet.setDescription(describe);
		taskGet.setPic_address(picAddress);
		taskGet.setPersonal_inf(personInf);
		taskGet.setOthers(others);
		//更新状态为:已提交完成信息（3）
		taskGet.setStatus(3);
		return taskGetDao.updateTaskGet(taskGet);
	}

}
