package com.nianhong.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nianhong.model.User;
import com.nianhong.service.UserService;



public class UserTest {

private UserService userService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userServiceImpl");
	}
	
	@Test
	public void testGetTimeSortedTaskByType(){
		User user = new User();
		user.setUsername("yy13y");
//		System.out.println(userService.insertUser(user));
	}
}
