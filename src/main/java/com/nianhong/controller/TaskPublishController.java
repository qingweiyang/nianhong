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

import com.nianhong.model.Task;
import com.nianhong.model.Test;
import com.nianhong.service.RegionService;
import com.nianhong.service.SalerService;
import com.nianhong.service.impl.ServiceHelper;
import com.nianhong.vo.TaskVO;

@Controller
@RequestMapping("saler")
public class TaskPublishController {

	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	@ResponseBody
	public Test test(@RequestBody Test t) {
//		System.out.println("test... "+t.getTitle());
		System.out.println("test... "+t.getTitle());
		Test tt = new Test();
		tt.setTitle("good");
		tt.setContent("nice");
		return tt;
	}
	
	@RequestMapping(value = "publish.do", method = RequestMethod.POST)
	@ResponseBody
	public void publishTask(@RequestBody TaskVO st) {
		SalerService salerService = ServiceHelper.getSalerService();
		salerService.publishTask("杨庆苇", st);
	}
	
	/**
	 * 加载任务类别
	 * 
	 * @param st
	 */
	@RequestMapping(value = "loadType.do", method = RequestMethod.POST)
	@ResponseBody
	public List<String> loadType() {
		//TODO 加载任务类型需要判断是否为高级用户，如果为高级用户调用以下方法，否则对权限进行筛选（调用selectTypeByPrivilege(0)）
		SalerService salerService = ServiceHelper.getSalerService();
		return salerService.loadType();
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
