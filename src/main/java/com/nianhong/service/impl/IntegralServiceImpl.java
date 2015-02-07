package com.nianhong.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

	@Override
	public Message agreePayDeposit(String admin, int integralLogID) {
		Message msg = new Message();
		
		IntegralLog integralLog = integralLogDao.selectByID(integralLogID);
		Integral integral = integralDao.selectByUsername(integralLog.getInitiator());
		
		//判断申请金额是否大于用户流动积分总额
		double val = integralLog.getValue();
		if(val > integral.getValue()) {
			//申请金额是否大于用户流动积分总额，操作失败
			 msg.setStatus(false);
			 String cont = "error:用户申请金额："+val+",高于该用户流动金额："+integral.getValue();
			 msg.addCont(cont);
			 System.out.println(cont);
			 integralLog.setStatus(4);
			 integralLog.setRemark(cont);
			 return msg;
		}
		
		//同意申请操作
		//integral将value值存放到deposit中
		integral.setValue(integral.getValue()-val);
		integral.setDeposit(integral.getDeposit()+val);
		if(!integralDao.updateIntegral(integral)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralDao.updateIntegral(integral)");
		}
		
		//修改integral_log status
		integralLog.setStatus(2);
		integralLog.setAccepter(admin);
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		if(!integralLogDao.updateIntegralLog(integralLog)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralLogDao.updateIntegralLog(integralLog)");
		}
		
		return msg;
	}

	@Override
	public Message refusePayDeposit(String admin, int integralLogID) {
		Message msg = new Message();
		
		IntegralLog integralLog = integralLogDao.selectByID(integralLogID);
		integralLog.setStatus(3);
		integralLog.setAccepter(admin);
		integralLog.setRemark("管理员拒绝");
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		
		if(!integralLogDao.updateIntegralLog(integralLog)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralLogDao.updateIntegralLog(integralLog)");
		}
		
		return msg;
	}

	@Override
	public Message agreeUnfreezeDeposit(String admin, int integralLogID) {
		Message msg = new Message();
		
		IntegralLog integralLog = integralLogDao.selectByID(integralLogID);
		Integral integral = integralDao.selectByUsername(integralLog.getInitiator());
		
		//对用户积分解冻操作，更新integral表数据(这里默认将deposit值全部解冻)
		integral.setValue(integral.getValue()+integral.getDeposit());
		integral.setDeposit(0);
		if(!integralDao.updateIntegral(integral)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralDao.updateIntegral(integral)");
		}
		
		//修改integral_log状态
		integralLog.setStatus(2);
		integralLog.setAccepter(admin);
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		if(!integralLogDao.updateIntegralLog(integralLog)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralLogDao.updateIntegralLog(integralLog)");
		}
		
		return msg;
	}

	@Override
	public Message refuseUnfreezeDeposit(String admin, int integralLogID) {
		Message msg = new Message();
		
		IntegralLog integralLog = integralLogDao.selectByID(integralLogID);
		integralLog.setStatus(3);
		integralLog.setAccepter(admin);
		integralLog.setRemark("管理员拒绝");
		integralLog.setOperate_time(Calendar.getInstance().getTime());
		
		if(!integralLogDao.updateIntegralLog(integralLog)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralLogDao.updateIntegralLog(integralLog)");
		}
		
		return msg;
	}

	@Override
	public List<Map<String, Object>> getIntegralIn() {
		return integralLogDao.selectTBByTypeAndStatus(1, 1);
	}

	@Override
	public List<Map<String, Object>> getIntegralUnfreeze() {
		return integralLogDao.selectTBByTypeAndStatus(2, 1);
	}

	@Override
	public Message reduceDeposit(String operator, String username, double value) {
		Message msg = new Message();
		
		Integral integral = integralDao.selectByUsername(username);
		//用户当前任务宝额度不足，扣减失败
		double actVal = integral.getDeposit();
		if(value > actVal) {
			msg.setStatus(false);
			String cont = "error:用户任务宝额度 "+actVal+",小于扣减额度："+value;
			msg.addCont(cont);
			return msg;
		}
		
		//扣减积分操作
		integral.setDeposit(actVal - value);
		if(!integralDao.updateIntegral(integral)) {
			msg.setStatus(false);
			msg.addCont("数据库更新错误，integralDao.updateIntegral(integral)");
		}
		
		return msg;
	}

}
