package com.nianhong.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.nianhong.service.BuyerService;
import com.nianhong.service.TaskService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.vo.SubTaskVO;

@Controller
@RequestMapping(value={"buyer"})
public class BuyerTaskController {
	
	private BuyerService buyerService = ServiceHelper.getBuyerService();
	
	private TaskService taskService = ServiceHelper.getTaskService();

	/**
	 * 加载买家所有待审核的任务
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadVerifyTask.do", method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> loadVerifyTask() {
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
		List<HashMap<String, Object>> res = buyerService.selectBuyerTask(LoginInf.username, 5);
		
		return res;
	}
	
	/**
	 * 根据subTaskID加载具体的任务信息
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadSubTaskInf.do", method = RequestMethod.POST)
	@ResponseBody
	public SubTaskVO loadSubTaskInf(int subtaskid) {
		return taskService.getTaskVOBySubtaskID(LoginInf.username, subtaskid);
	}
	
	/**
	 * 买家完成任务后，提交完成任务的信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "submitTaskInf.do")
	public String submitTaskInf(HttpServletRequest request,HttpServletResponse response) {//获取子任务id
		//获取子任务的id(通过在form表单中增加隐藏input获取)
		int taskGetID = Integer.parseInt(request.getParameter("taskGetID"));
		//获取前端文字描述信息
		String description = request.getParameter("description");
		//获取个人信息(三项，按2进制结果表示)
		int personInf = 0;
		if(null != request.getParameter("inlineCheckbox1")) {
			//银行卡
			personInf += 4;
		}
		if(null != request.getParameter("inlineCheckbox2")) {
			//财富通
			personInf += 2;
		}
		if(null != request.getParameter("inlineCheckbox3")) {
			//支付宝
			personInf += 1;
		}
		
		//其他信息
		String others = "";
		if(null != request.getParameter("inlineCheckbox4")) {
			others = request.getParameter("inlineCheckbox4");
		}
		
		//------以下进行图片上传，以及将所有图片上传路径保存在数据库中
		String picPath = "";
		String realpath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(realpath);
		//判断用户提交图片的文件夹是否存在(默认所有图片放在upload/文件夹中,文件名＝taskGetID+filename)
		String folder = realpath+"upload/";
		File uploadFolder  = new File(folder);
		if(!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}
		//创建一个通用的多部分解析器.   
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 //判断 request 是否有文件上传,即多部分请求...  
		if(multipartResolver.isMultipart(request))
		{
			 //判断 request 是否有文件上传,即多部分请求...  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
			
			java.util.Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next());
				String fileName = taskGetID+"_"+file.getOriginalFilename();
				if(null == fileName || fileName.equals("")) {
					//未上传图片，可接受，跳出循环
					break;
				}
				String path =folder +fileName;
				//所有图片路径保存在数据库中为相对路径，及文件名称，取数据时加上folder
				picPath += fileName+"&";
				File localFile = new File(path);
				System.out.println(path);
				try {
					file.transferTo(localFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}		
		//调用上传信息服务
		buyerService.submitTask(taskGetID, description, picPath, personInf, others);
		return "buyer/submitTaskEnd";
	}
}
