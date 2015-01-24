package com.nianhong.model;

import java.util.Date;

/**
 * 对应页面 我是雇主 我的任务 任务中 vo
 * @author yqw
 *
 */
public class SalerTaskDoing {

	private String id;
	
	private Date publish_time;
	
	private String brief;
	
	private String accepter;
	
	private Date accept_time;
	
	private int status;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	
}
