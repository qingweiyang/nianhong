package com.nianhong.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nianhong.service.BuyerService;
import com.nianhong.service.IntegralService;
import com.nianhong.service.RegionService;
import com.nianhong.service.SalerService;
import com.nianhong.service.TaskService;
import com.nianhong.service.UserService;

public class ServiceHelper{

	private static ApplicationContext context = 
			new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml","classpath:conf/spring-mybatis.xml"});
	
	public static SalerService getSalerService() {
		return (SalerService) context.getBean("salerServiceImpl");
	}

	public static RegionService getRegionService() {
		return (RegionService) context.getBean("regionServiceImpl");
	}
	
	public static UserService getUserService() {
		return (UserService) context.getBean("userServiceImpl");
	}
	
	public static TaskService getTaskService() {
		return (TaskService) context.getBean("taskServiceImpl");
	}
	
	public static BuyerService getBuyerService() {
		return (BuyerService) context.getBean("buyerServiceImpl");
	}
	
	public static IntegralService getIntegralService() {
		return (IntegralService) context.getBean("integralServiceImpl");
	}
}
