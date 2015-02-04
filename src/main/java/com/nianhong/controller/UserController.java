package com.nianhong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;

@Controller
@RequestMapping(value={"user"})
public class UserController {
	private TaskService taskService = ServiceHelper.getTaskService();
	
	@RequestMapping(value = "getDealRecord.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDealRecord(String publisher, String accepter) {
		List<Map<String, Object>> res = taskService.getDealRecord(publisher, accepter);
		return res;
	}
	
}
