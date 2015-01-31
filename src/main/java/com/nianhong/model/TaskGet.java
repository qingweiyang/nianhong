package com.nianhong.model;

import java.util.Date;

public class TaskGet {
	private int id;
	
	private String task_id;
	
	private int sub_task_id;
	
	private String accepter;
	
	private Date accept_time;
	
	private Date finish_time;
	
	private int status;
	
	private String remark;

	//买家提交任务信息 ： 文字描述
	private String description;
	
	//买家提交任务信息 ： 图片详情（保存图片存储地址）（多张图片用“&”分隔符）
	private String pic_address;
	
	//个人信息；银行卡、财富通、支付宝（000），如果只有银行卡为100（2进制表示）
	private int personal_inf;
	
	//买家提交任务信息 ： 其他信息（可选项）
	private String others;
	
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

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public Date getAccept_time() {
		return accept_time;
	}

	public void setAccept_time(Date accept_time) {
		this.accept_time = accept_time;
	}

	public int getSub_task_id() {
		return sub_task_id;
	}

	public void setSub_task_id(int sub_task_id) {
		this.sub_task_id = sub_task_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	
	public String getPic_address() {
		return pic_address;
	}

	public void setPic_address(String pic_address) {
		this.pic_address = pic_address;
	}

	public int getPersonal_inf() {
		return personal_inf;
	}

	public void setPersonal_inf(int personal_inf) {
		this.personal_inf = personal_inf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
	
	
}
