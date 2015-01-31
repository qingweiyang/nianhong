package com.nianhong.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nianhong.model.Task;
import com.nianhong.model.User;
import com.nianhong.model.WaitVerify;
import com.nianhong.service.RegionService;
import com.nianhong.service.SalerService;
import com.nianhong.service.UserService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		SalerService s = (SalerService) context.getBean("salerServiceImpl");
		System.out.println(s.agreeAccepter("20150120144506", "路人家"));
		System.out.println(System.getProperty("user.dir"));
//		List<HashMap<String, Object>> mod = s.test("杨庆苇", 1);
//		System.out.println(mod.get(0).get("publish_time"));
//		s.selectByPublisherAndStatus("yqw", 1);
		
//		RegionService userService = (RegionService) context.getBean("regionServiceImpl");
//		List<String> val = userService.loadCity("江苏省");
		
//		System.out.println(System.currentTimeMillis());
//		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
//		System.out.println(sf.format(new Date()));
//		Calendar now = Calendar.getInstance();
//		System.out.println(sf.format(now.getTime()));
//		List<Task> tasks = ServiceHelper.getSalerService().getSalerTaskByStatus(1);
//		System.out.println(sf.format(tasks.get(0).getPublish_time()));
	}

}
