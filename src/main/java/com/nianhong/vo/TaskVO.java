package com.nianhong.vo;

import java.io.Serializable;
import java.util.List;

import com.nianhong.model.Task;

public class TaskVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//任务标题
	private String title;
	
	private String brief;
	
	private String type;
	
	//任务佣金
	private double commission;

	//买家垫付金额
	private double advanced;
	
	//是否需要审核：是（1）、否（0）；
	private int verify;
	
	private double buyerFreeze;

	private double salerFreeze;

	private double reward;

	private int personNeed;

	private String detail;
	
	private Task taskModel;
	
	private List<SubTaskVO> subtask;

	//任务领取的状态［确认领取，待雇主审核，任务已领取］
	private String accStatus;
	
	public List<SubTaskVO> getSubtask() {
		return subtask;
	}

	public void setSubtask(List<SubTaskVO> subtask) {
		this.subtask = subtask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAdvanced() {
		return advanced;
	}

	public void setAdvanced(double advanced) {
		this.advanced = advanced;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

	public double getBuyerFreeze() {
		return buyerFreeze;
	}

	public void setBuyerFreeze(double buyerFreeze) {
		this.buyerFreeze = buyerFreeze;
	}

	public double getSalerFreeze() {
		return salerFreeze;
	}

	public void setSalerFreeze(double salerFreeze) {
		this.salerFreeze = salerFreeze;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public int getPersonNeed() {
		return personNeed;
	}

	public void setPersonNeed(int personNeed) {
		this.personNeed = personNeed;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public Task getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(Task taskModel) {
		this.taskModel = taskModel;
	}

	
	
}
