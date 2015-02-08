package com.nianhong.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nianhong.service.RegionService;
import com.nianhong.service.SalerService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.util.LoginInf;
import com.nianhong.util.Message;
import com.nianhong.vo.TaskVO;

@Controller
@RequestMapping(value={"/saler", "/taskRoom"})
public class TaskPublishController {

	@RequestMapping(value = "publish.do", method = RequestMethod.POST)
	@ResponseBody
	public Message publishTask(@RequestBody TaskVO task) {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.publishTask(LoginInf.username, task);
	}
	
	/**
	 * 加载任务类别
	 * 	根据用户权限加载用户拥有的任务类别
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadType.do", method = RequestMethod.POST)
	@ResponseBody
	public List<String> loadType() {
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.loadType(LoginInf.username);
	}

	@RequestMapping(value = "loadProvince.do", method = RequestMethod.POST)
	public void loadProvince(HttpServletRequest request, HttpServletResponse response) throws IOException{
		RegionService regionService = ServiceHelper.getRegionService();
		List<String> val = regionService.loadProvince();
		
		PrintWriter out = response.getWriter();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(  
                System.out, JsonEncoding.UTF8);
		jsonGenerator.writeObject(val);
		out.println(objectMapper.writeValueAsString(val));
	}
	
	@RequestMapping(value = "loadCity.do", method = RequestMethod.POST)
	public void loadCity(HttpServletRequest request, HttpServletResponse response) throws IOException{
		RegionService regionService = ServiceHelper.getRegionService();
		List<String> val = regionService.loadCity(request.getParameter("province"));
		
		PrintWriter out = response.getWriter();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(  
                System.out, JsonEncoding.UTF8);
		jsonGenerator.writeObject(val);
		out.println(objectMapper.writeValueAsString(val));
	}
}
