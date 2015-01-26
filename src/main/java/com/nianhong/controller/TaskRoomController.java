package com.nianhong.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.Task;
import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;

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
	
	@RequestMapping(value = "loadTaskTable.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Task> loadTaskTable(String type) {
		return taskService.getTimeSortedTaskByType(type);
	}
}
