package com.nianhong.model;

import java.util.Date;

public class Task {
	
	private String id;

	private String title;
		
	private String publisher;
	
	private String brief;
	
	private String type;
	
	//任务佣金
	private double commission;
	
	//垫付金额
	private double advanced;

	private int verify;
	
	private double buyer_freeze;

	private double saler_freeze;

	private double reward;

	private int person_need;

	private String detail;

	private int status;

	private Date publish_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAdvanced() {
		return advanced;
	}

	public void setAdvanced(double advanced) {
		this.advanced = advanced;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getBuyer_freeze() {
		return buyer_freeze;
	}

	public void setBuyer_freeze(double buyer_freeze) {
		this.buyer_freeze = buyer_freeze;
	}

	public double getSaler_freeze() {
		return saler_freeze;
	}

	public void setSaler_freeze(double saler_freeze) {
		this.saler_freeze = saler_freeze;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public int getPerson_need() {
		return person_need;
	}

	public void setPerson_need(int person_need) {
		this.person_need = person_need;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	
}
