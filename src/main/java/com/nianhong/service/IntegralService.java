package com.nianhong.service;

import java.util.List;

import com.nianhong.model.Integral;
import com.nianhong.model.IntegralLog;
import com.nianhong.util.Message;

public interface IntegralService {
	
	/**
	 * 缴纳保证金操作
	 * 
	 * @param payer
	 * @param val
	 * @return
	 */
	public Message payDeposit(String payer, double val);
	
	/**
	 * 解冻保证金（默认所有冻结金额）
	 * 
	 * @param unfreezer
	 * @return
	 */
	public Message unfreezeDeposit(String unfreezer, String reason);
	
	/**
	 * 判断用户是否已经申请过 解冻保证金
	 * 
	 * @param unfreezer
	 * @return
	 */
	public List<IntegralLog> unfreezeDepositStatus(String unfreezer);
	
	/**
	 * 查询保证金
	 * 
	 * @param user
	 * @return 
	 */
	public Integral getDeposit(String user);
	
	/**
	 * 判断是否用户有缴纳的保证金，且该保证金还未通过审核
	 * 
	 * @param initator
	 * @param type
	 * @param status
	 * @return
	 */
	public List<IntegralLog> payDepositStatus(String initiator);
}
