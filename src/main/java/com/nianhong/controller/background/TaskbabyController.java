package com.nianhong.controller.background;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.service.IntegralService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.util.Message;

/**
 * 后台 任务宝 controller页面
 * 
 * @author yqw
 *
 */
@Controller
@RequestMapping(value={"admin/task"})
public class TaskbabyController {

	private IntegralService integralService = ServiceHelper.getIntegralService();
	
	/**
	 * 加载用户申请加入任务宝信息列表
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadDepositIn.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadDepositIn() {
		return integralService.getIntegralIn();
	}
	
	/**
	 * 加载用户申请解冻任务宝信息列表
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadDepositOut.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> loadDepositOut() {
		return integralService.getIntegralUnfreeze();
	}
	
	/**
	 * 拒绝用户申请加入任务宝
	 * 
	 * @param st
	 */
	@RequestMapping(value = "refusePayDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message refusePayDeposit(int id) {
		return integralService.refusePayDeposit(LoginInf.username, id);
	}
	
	/**
	 * 同意用户申请加入任务宝
	 * 
	 * @param st
	 */
	@RequestMapping(value = "agreePayDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message agreePayDeposit(int id) {
		return integralService.agreePayDeposit(LoginInf.username, id);
	}
	
	/**
	 * 同意用户申请解冻任务宝
	 * 
	 * @param st
	 */
	@RequestMapping(value = "agreeUnfreezeDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message agreeUnfreezeDeposit(int id) {
		return integralService.agreeUnfreezeDeposit(LoginInf.username, id);
	}
	
	/**
	 * 扣减用户任务宝积分
	 * 
	 * @param st
	 */
	@RequestMapping(value = "reduceDeposit.do", method = RequestMethod.POST)
	@ResponseBody
	public Message reduceDeposit(String username, double value) {
		return integralService.reduceDeposit(LoginInf.username, username, value);
	}
}
