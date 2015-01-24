package com.nianhong.vo;

import java.io.Serializable;
import java.util.Date;

public class SubTaskVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date startTime;

	private Date finishTime;

	private String province;

	private String city;

	private int subNum;

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

	
}
