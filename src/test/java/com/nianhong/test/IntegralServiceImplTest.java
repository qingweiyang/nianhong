package com.nianhong.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nianhong.service.IntegralService;
import com.nianhong.service.impl.ServiceHelper;

public class IntegralServiceImplTest {
	
	@Autowired 
	private IntegralService integralService;
	
	
	
	@Before
	public void before() {
		integralService = ServiceHelper.getIntegralService();
	}
	
	@Test
	public void payDepositTest() {
		integralService.payDeposit("买家", 10.9);
	}
	
	@Test 
	public void unfreezeDepositTest() {
		integralService.unfreezeDeposit("买家", "just soso");
	}
	
	@Test 
	public void agreePayDepositTest() {
		integralService.agreePayDeposit("123", 35);
	}
}
