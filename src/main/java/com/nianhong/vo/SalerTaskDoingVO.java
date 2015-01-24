package com.nianhong.vo;

import java.util.Date;

/**
 * 
 * @author yqw
 *
 * 对应页面 我是雇主>我的任务>任务中
 * 
 */
public class SalerTaskDoingVO {
	private String id;
	
	private Date publish_time;
	
	private String brief;
	
	private String accepter;
	
	private Date accept_time;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
