package com.nianhong.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nianhong.dao.TaskDao;
import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.User;
import com.nianhong.service.TaskService;
import com.nianhong.service.UserService;
import com.nianhong.vo.TaskVO;

public class TaskTest {
	private TaskService taskService;
	
	@Autowired
	private TaskDao taskDao;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		taskService = (TaskService) context.getBean("taskServiceImpl");
	}
	
	@Test
	public void testGetTimeSortedTaskByType(){
		List<Task> tasks = taskService.getTimeSortedTaskByType("会员专享");
		for(Task t : tasks) {
			System.out.println(t.getPublish_time());
		}
	}
	
	@Test
	public void testGetTaskVOByID() {
		TaskVO tvs = taskService.getTaskVOByID("张三", "20150120144506");
		System.out.println(tvs.getAccStatus());
		System.out.println(tvs.getSubtask().get(0).getActSubNum());
	}
	
	@Test
	public void testSelectTaskByID() {
		Task t = taskService.getTaskByID("20150120192510");
		System.out.println(t.getSubTasks().size());
	}
}
