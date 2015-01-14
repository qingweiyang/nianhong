package com.nianhong.model;

import java.util.Date;

public class Task {
	
	private int id;

	private String publisher;
	
	private String brief;
	
	private String areaProvince;
	
	private String areaCity;

	private int verify;

	private int personNeed;

	private String detail;

	private int status;

	private Date publishTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAreaProvince() {
		return areaProvince;
	}

	public void setAreaProvince(String areaProvince) {
		this.areaProvince = areaProvince;
	}

	public String getAreaCity() {
		return areaCity;
	}

	public void setAreaCity(String areaCity) {
		this.areaCity = areaCity;
	}

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	
}
