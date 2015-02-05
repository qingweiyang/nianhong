package com.nianhong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.Integral;
import com.nianhong.model.IntegralLog;
import com.nianhong.service.IntegralService;
import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.util.Message;

@Controller
@RequestMapping(value={"user"})
public class UserController {
	private TaskService taskService = ServiceHelper.getTaskService();
	
	private IntegralService integralService = ServiceHelper.getIntegralService();
	
	/**
	 * 获取雇主与买家的交易记录
	 * 
	 * @param publisher
	 * @param accepter
	 * @return
	 */
	@RequestMapping(value = "getDealRecord.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDealRecord(String publisher, String accepter) {
		List<Map<String, Object>> res = taskService.getDealRecord(publisher, accepter);
		return res;
	}
	
	/**
	 * 用户缴纳保证金
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "payDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message payDeposit(double val) {	
		return integralService.payDeposit(LoginInf.username, val);
	}
	
	/**
	 * 检查用户缴纳保证金状态
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "payDepositStatus.do", method = RequestMethod.GET)
	@ResponseBody
	public List<IntegralLog> payDepositStatus() {	
		return integralService.payDepositStatus(LoginInf.username);
	}
	
	/**
	 * 用户解冻保证金
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "unfreezeDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message unfreezeDeposit(String reason) {	
		return integralService.unfreezeDeposit(LoginInf.username, reason);
	}
	
	/**
	 * 检查用户 解冻保证金 申请状态
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "unfreezeDepositStatus.do", method = RequestMethod.GET)
	@ResponseBody
	public List<IntegralLog> unfreezeDepositStatus() {	
		return integralService.unfreezeDepositStatus(LoginInf.username);
	}
	
	/**
	 * 查询用户的保证金
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryDeposit.do", method = RequestMethod.GET)
	@ResponseBody
	public Integral queryDeposit() {
		return integralService.getDeposit(LoginInf.username);
	}
}
