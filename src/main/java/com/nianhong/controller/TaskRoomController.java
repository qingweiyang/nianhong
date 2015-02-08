package com.nianhong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.Task;
import com.nianhong.service.BuyerService;
import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.vo.SalerInfVO;
import com.nianhong.vo.TaskVO;

/**
 * 任务大厅控制器
 * 
 * @author yqw
 *
 */

@Controller
@RequestMapping("taskRoom")
public class TaskRoomController {
	
	private TaskService taskService = ServiceHelper.getTaskService();
	
	private BuyerService buyerService = ServiceHelper.getBuyerService();
	
	@RequestMapping(value = "loadTaskTable.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Task> loadTaskTable(String type) {
		return taskService.getTimeSortedTaskByType(type);
	}
	
	@RequestMapping(value = "loadTaskInf.do", method = RequestMethod.POST)
	@ResponseBody
	public TaskVO loadTaskInf(String taskID, String accepter) {
		return taskService.getTaskVOByID(accepter, taskID);
	}
	
	@RequestMapping(value = "acceptTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> acceptTask(int id, String accepter) {
		return buyerService.acceptTask(accepter, id);
	}
	
	@RequestMapping(value = "loadSalerInf.do", method = RequestMethod.POST)
	@ResponseBody
	public SalerInfVO loadSalerInf(String taskID) {
		return taskService.getSalerInf(taskID, LoginInf.username);
	}
	
}
