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
	
}
