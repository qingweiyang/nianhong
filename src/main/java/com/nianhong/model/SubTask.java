package com.nianhong.model;

import java.util.Date;

public class SubTask {

	private int id;
	
	private String task_id;
	
	private Date start_time;

	private Date finish_time;

	private String province;

	private String city;

	private int person_need;

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

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
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

	public int getPerson_need() {
		return person_need;
	}

	public void setPerson_need(int person_need) {
		this.person_need = person_need;
	}
	
	
}
