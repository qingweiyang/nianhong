package com.nianhong.vo;

import java.io.Serializable;
import java.util.Date;

import com.nianhong.model.SubTask;

public class SubTaskVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String task_id;

	private Date startTime;

	private Date finishTime;

	private String province;

	private String city;

	private int subNum;
	
	private SubTask subTaskModel;
	
	//实际子任接受者务数量（状态1～4，排除已删除与交易失败的状态）
	private int actSubNum;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSubNum() {
		return subNum;
	}

	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}

	public int getActSubNum() {
		return actSubNum;
	}

	public void setActSubNum(int actSubNum) {
		this.actSubNum = actSubNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public SubTask getSubTaskModel() {
		return subTaskModel;
	}

	public void setSubTaskModel(SubTask subTaskModel) {
		this.subTaskModel = subTaskModel;
	}

	
}
