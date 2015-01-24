package com.nianhong.model;

import java.util.Date;

/**
 * 对应 我是雇主 我的任务 待审核 vo
 * @author yqw
 *
 */
public class WaitVerify {

	/**
	 * 	<select id="selectWaitVerifyModel" resultType="Task">
		select task.id, task.publish_time, task.brief,  task_get.accepter, task_get.accept_time, user.area
		from task, task_get, user
		where task.publisher = #{publisher} and task.status = #{status} and task.id = task_get.task_id and task_get.accepter=user.username
	</select>
	 */
	private String id;
	
	private Date publish_time;
	
	private String brief;
	
	private String accepter;
	
	private Date accept_time;
	
	private String area;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	
}
