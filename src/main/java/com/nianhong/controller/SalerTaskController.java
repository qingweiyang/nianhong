package com.nianhong.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.model.WaitVerify;
import com.nianhong.service.SalerService;
import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.vo.TaskGetVO;

@Controller
@RequestMapping("saler")
public class SalerTaskController {
	
	private TaskService taskService = ServiceHelper.getTaskService();

	/**
	 * 加载雇主所有等待的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadWaitedTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<WaitVerify> loadWaitedTask() {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.selectWaitVerifyTask(LoginInf.username);
	}
	
	/**
	 * 加载雇主所有待审核的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadVerifyTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadVerifyTask() {
		SalerService salerService = ServiceHelper.getSalerService();
		List<HashMap<String, Object>> res = salerService.selectSalerTask(LoginInf.username, 1);
		return res;
	}

	/**
	 * 加载雇主所有任务中的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadDoingTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadDoingTask() {
		SalerService salerService = ServiceHelper.getSalerService();
		//获取用户正在进行的任务
		List<HashMap<String, Object>> res = salerService.selectSalerTask(LoginInf.username, 2);
		//获取用户已提交完成信息，等待雇主确认的任务
		res.addAll(salerService.selectSalerTask(LoginInf.username, 3));
		return res;
	}
	
	/**
	 * 加载雇主所有完成的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadFinishTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadFinishTask() {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.selectSalerTask(LoginInf.username, 4);
	}
	
	/**
	 * 加载雇主所有失败的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadFailTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadFailTask() {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.selectSalerTask(LoginInf.username, 5);
	}
	
	/**
	 * 删除一条任务
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "deleteTask.do", method = RequestMethod.POST)
	public void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SalerService salerService = ServiceHelper.getSalerService();
		String id = request.getParameter("id");
		salerService.deletetTaskByID(id);
		
		PrintWriter out = response.getWriter();

		out.println("success");
	}
	
	/**
	 * 雇主同意买家领取任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "agreeAccepter.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean agreeAccepter(String accepter, String taskID) {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.agreeAccepter(taskID, accepter);
	}
	
	/**
	 * 雇主拒绝买家领取任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "refuseAccepter.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean refuseAccepter(String accepter, String taskID) {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.refuseAccepter(taskID, accepter);
	}
	
	/**
	 * 雇主中止交易
	 * 
	 * @param st
	 */
	@RequestMapping(value = "suspendDeal.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean suspendDeal(String taskGetID) {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.suspendDeal(taskGetID);
	}
	
	/**
	 * 雇主确认交易完成
	 * 
	 * @param st
	 */
	@RequestMapping(value = "sureDeal.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean sureDeal(String taskGetID) {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.sureDeal(taskGetID);
	}
	
	/**
	 * 获取买家提交的任务完成信息
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadBuyerSubmitInf.do", method = RequestMethod.POST)
	@ResponseBody
	public TaskGetVO loadBuyerSubmitInf(int taskGetID) {
		return taskService.getTaskGetVO(taskGetID);
	}
	
	/**
	 * 加载图片
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getPic.do", method = RequestMethod.GET)
	public void getPic(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException{
		fileName = request.getSession().getServletContext().getRealPath("/")+"upload/"+fileName;
		File file = new File(fileName);
		System.out.println(fileName);
        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
            file = new File(request.getSession().getServletContext().getRealPath("/")
                    + "resource/icons/auth/root.png");
        }
        
        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        inputStream.close();

        response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
        
	}
}
