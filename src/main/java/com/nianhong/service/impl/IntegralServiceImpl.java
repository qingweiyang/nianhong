package com.nianhong.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.IntegralDao;
import com.nianhong.dao.IntegralLogDao;
import com.nianhong.model.Integral;
import com.nianhong.model.IntegralLog;
import com.nianhong.service.IntegralService;
import com.nianhong.util.Message;

@Service
public class IntegralServiceImpl implements IntegralService{
	@Autowired
	private IntegralDao integralDao;
	
	@Autowired
	private IntegralLogDao integralLogDao;
	
	@Override
	public Message payDeposit(String payer, double val) {
		Message msg = new Message();
		//判断payer是否有足够的积分缴纳保证金
		Integral integral = integralDao.selectByUsername(payer);
		if(integral.getValue() < val) {
			//payer拥有的积分不足缴纳保证金
			msg.setStatus(false);
			String cont = "您当前有流动积分："+integral.getValue()+",积分不足,操作失败！";
			System.out.println(cont);
			msg.addCont(cont);
			return msg;
		}
		
		//记录缴纳积分日志
		IntegralLog integralLog = new IntegralLog();
		integralLog.setInitiator(payer);
		integralLog.setValue(val);
		//操作类型：1（缴纳保证金），2（解冻保证金）
		integralLog.setType(1);
		//操作状态：0（操作动作结束），1（操作动作进行中，eg解冻保证金等待审核）
		integralLog.setStatus(1);
		//记录当前时间
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		//执行插入数据库动作
		if(!integralLogDao.insertIntegralLog(integralLog)) {
			//插入数据库失败
			msg.setStatus(false);
			String cont = "插入数据库integral_log表失败！";
			System.out.println(cont);
			msg.addCont(cont);
		}
		
		return msg;
	}

	@Override
	public List<IntegralLog> payDepositStatus(String initiator) {
		return integralLogDao.selectByInitiatorAndTypeAndStatus(initiator, 1, 1);
	}
	
	@Override
	public Message unfreezeDeposit(String unfreezer, String reason) {
		Message msg = new Message();
		
		IntegralLog integralLog = new IntegralLog();
		integralLog.setInitiator(unfreezer);
		//操作类型：1（缴纳保证金），2（解冻保证金）
		integralLog.setType(2);
		//操作状态：0（操作动作结束），1（操作动作进行中，eg解冻保证金等待审核）
		integralLog.setStatus(1);
		//记录当前时间
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		integralLog.setRemark(reason);
		if(!integralLogDao.insertIntegralLog(integralLog)) {
			//插入数据库失败
			msg.setStatus(false);
			String cont = "插入数据库integral_log表失败！";
			System.out.println(cont);
			msg.addCont(cont);
		}
			
		return msg;
	}

	@Override
	public Integral getDeposit(String user) {
		return integralDao.selectByUsername(user);
	}

	@Override
	public List<IntegralLog> unfreezeDepositStatus(String unfreezer) {
		return integralLogDao.selectByInitiatorAndTypeAndStatus(unfreezer, 2, 1);
	}

}
