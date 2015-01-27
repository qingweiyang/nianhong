package com.nianhong.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.service.BuyerService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;

@Controller
@RequestMapping("buyer")
public class BuyerTaskController {

	/**
	 * 加载买家所有待审核的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadVerifyTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadVerifyTask() {
		BuyerService buyerService = ServiceHelper.getBuyerService();
		List<HashMap<String, Object>> res = buyerService.selectBuyerTask(LoginInf.username, 1);
		return res;
	}
	
	/**
	 * 加载买家所有任务中的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadDoingTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadDoingTask() {
		BuyerService buyerService = ServiceHelper.getBuyerService();
		List<HashMap<String, Object>> res = buyerService.selectBuyerTask(LoginInf.username, 2);
		
		return res;
	}
	
	/**
	 * 加载买家所有完成的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadFinishTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadFinishTask() {
		BuyerService buyerService = ServiceHelper.getBuyerService();
		//已提交完成信息的任务
		List<HashMap<String, Object>> res = buyerService.selectBuyerTask(LoginInf.username, 3);
		//交易完成的任务
		res.addAll(buyerService.selectBuyerTask(LoginInf.username, 4));
		return res;
	}
	
	/**
	 * 加载买家所有任务失败的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadFailTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadFailTask() {
		BuyerService buyerService = ServiceHelper.getBuyerService();
		List<HashMap<String, Object>> res = buyerService.selectBuyerTask(LoginInf.username, 5);
		
		return res;
	}
}
