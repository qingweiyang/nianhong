package com.nianhong.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nianhong.service.BuyerService;

public class BuyerServiceImplTest {
	@Autowired
	private BuyerService buyerService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		buyerService = (BuyerService) context.getBean("buyerServiceImpl");
	}
	
	@Test
	public void acceptTask() {
		buyerService.acceptTask("路人", 2);
	}
}
