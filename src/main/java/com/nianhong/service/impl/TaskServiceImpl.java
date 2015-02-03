package com.nianhong.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.SubTaskDao;
import com.nianhong.dao.TaskDao;
import com.nianhong.dao.TaskGetDao;
import com.nianhong.dao.UserDao;
import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.TaskGet;
import com.nianhong.model.User;
import com.nianhong.service.TaskService;
import com.nianhong.util.LoginInf;
import com.nianhong.vo.SalerInfVO;
import com.nianhong.vo.SubTaskVO;
import com.nianhong.vo.TaskGetVO;
import com.nianhong.vo.TaskVO;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private SubTaskDao subTaskDao;
	@Autowired 
	private UserDao userDao;
	@Autowired 
	private TaskGetDao taskGetDao;
	
	@Override
	public List<Task> getTimeSortedTaskByType(String type) {
		//选择未删除的所有任务
		List<Task> tasks = taskDao.selectByStatusAndType(1, type);
		
		Collections.sort(tasks, new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {
				return o1.getPublish_time().before(o2.getPublish_time()) ? 1:-1;
			}
		});
		return tasks;
	}

	@Override
	public Task getTaskByID(String taskID) {
		Task task = taskDao.selectTaskByID(taskID);
		if(null == task)
			return null;
		task.setSubTasks(subTaskDao.selectSubTaskByTaskID(taskID));
		return task;
	}

	@Override
	public SalerInfVO getSalerInf(String username) {
		SalerInfVO sale = new SalerInfVO();
		User u = userDao.selectByUsername(username);
		sale.setSalerName(username);
		
		return sale;
	}

	@Override
	public TaskVO getTaskVOByID(String accepter, String taskID) {
		//accepter被认定为登入用户
		accepter = LoginInf.username;
		
		TaskVO taskVO = new TaskVO();
		taskVO.setTaskModel(taskDao.selectTaskByID(taskID));
		
		//判断该任务接受者是否已领取
		List<TaskGet> tgs = taskGetDao.selectByAccepterAndTaskID(accepter, taskID);
		if(0 == tgs.size()) {
			//未领取
			taskVO.setAccStatus("确认领取");
		} else {
			//由每个用户同一条任务只能领取一个保证
			TaskGet tg = tgs.get(0);
			if(tg.getStatus() == 1)
				taskVO.setAccStatus("待雇主审核");
			else {
				taskVO.setAccStatus("任务已领取");
			}
		}
		
		//获取该任务的子任务vo
		List<SubTask> sbs = subTaskDao.selectSubTaskByTaskID(taskID);
		List<SubTaskVO> sbsvo = new ArrayList<SubTaskVO>();
		for(SubTask sb : sbs) {
			SubTaskVO svo = new SubTaskVO();
			svo.setSubTaskModel(sb);
			//获取该子任务实际已接受人数
			int actNum = taskGetDao.selectBySubTaskIDAndStatus(sb.getId(), 1).size();
			actNum += taskGetDao.selectBySubTaskIDAndStatus(sb.getId(), 2).size();
			actNum += taskGetDao.selectBySubTaskIDAndStatus(sb.getId(), 3).size();
			actNum += taskGetDao.selectBySubTaskIDAndStatus(sb.getId(), 4).size();
			svo.setActSubNum(actNum);
			sbsvo.add(svo);
		}
		
		taskVO.setSubtask(sbsvo);
		
		return taskVO;
	}

	@Override
	public SubTaskVO getTaskVOBySubtaskID(String accepter, int subtaskid) {
		SubTaskVO stvo = new SubTaskVO();
		SubTask st = subTaskDao.selectByID(subtaskid);
		stvo.setSubTaskModel(st);
		stvo.setTaskModel(taskDao.selectTaskByID(st.getTask_id()));
		
		return stvo;
	}

	@Override
	public TaskGetVO getTaskGetVO(int taskGetID) {
		
		TaskGet tg = taskGetDao.selectByID(taskGetID);
		TaskGetVO tgvo = new TaskGetVO();
		if(tg == null) {
			return null;
		} 
		tgvo.setTaskGetModel(tg);
		tgvo.setTaskModel(taskDao.selectTaskByID(tg.getTask_id()));
		tgvo.setSubTaskModel(subTaskDao.selectByID(tg.getSub_task_id()));
		
		//获取图片详情
		String path = tg.getPic_address();
		tgvo.setPicPaths(path.split("&"));
		//获取个人信息
		User u = userDao.selectByUsername(tg.getAccepter());
		int inf = tg.getPersonal_inf();
		Map<String, Object> perInf = new HashMap<String, Object>();
		if((inf&4) != 0) {
			//拥有银行卡信息
			perInf.put("银行卡", u.getBanknumber());
		}
		if((inf&2) != 0) {
			//拥有财富通信息
			perInf.put("财富通", u.getCaifutong());
		}
		if((inf&1) != 0) {
			//拥有支付宝信息
			perInf.put("支付宝", u.getZhifubao());
		}
		String others = tg.getOthers();
		if(null != others && (!others.equals(""))) {
			perInf.put("others", others);
		}
		
		tgvo.setPersonInf(perInf);
		
		return tgvo;
	}

	@Override
	public List<Map<String, Object>> getDealRecord(String check, String checked) {
		// TODO Auto-generated method stub
		return null;
	}

}
