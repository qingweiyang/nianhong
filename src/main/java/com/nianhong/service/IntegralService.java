package com.nianhong.service;

import java.util.List;
import java.util.Map;

import com.nianhong.model.Integral;
import com.nianhong.model.IntegralLog;
import com.nianhong.util.Message;

public interface IntegralService {
	
	/**
	 * 缴纳保证金申请操作
	 * 
	 * @param payer
	 * @param val
	 * @return
	 */
	public Message payDeposit(String payer, double val);

	/**
	 * 管理员通过用户申请保证金
	 * 
	 * @param admin
	 * @param integralLogID
	 * @return
	 */
	public Message agreePayDeposit(String admin, int integralLogID);
	
	/**
	 * 管理员拒绝用户申请保证金
	 * 
	 * @param admin
	 * @param integralLogID
	 * @return
	 */
	public Message refusePayDeposit(String admin, int integralLogID);
	
	/**
	 * 判断是否用户有缴纳的保证金，且该保证金还未通过审核
	 * 
	 * @param initator
	 * @param type
	 * @param status
	 * @return
	 */
	public List<IntegralLog> payDepositStatus(String initiator);
	
	/**
	 * 解冻保证金申请（默认所有冻结金额）
	 * 
	 * @param unfreezer
	 * @return
	 */
	public Message unfreezeDeposit(String unfreezer, String reason);
	
	/**
	 * 通过用户解冻任务宝
	 * 
	 * @param admin
	 * @param integralLogID
	 * @return
	 */
	public Message agreeUnfreezeDeposit(String admin, int integralLogID);
	
	/**
	 * 拒绝用户解冻任务宝
	 * 
	 * @param admin
	 * @param integralLogID
	 * @return
	 */
	public Message refuseUnfreezeDeposit(String admin, int integralLogID);
	
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
	 * 获取所有申请加入任务宝的用户信息(未被处理)
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getIntegralIn();

	/**
	 * 获取所有申请解冻任务宝的用户信息（未被处理）
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getIntegralUnfreeze();
	
	/**
	 * 扣减用户的任务宝积分
	 * 
	 * @param username
	 * @return
	 */
	public Message reduceDeposit(String operator, String username, double value);
	
}
