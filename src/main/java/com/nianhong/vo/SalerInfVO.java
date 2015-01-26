package com.nianhong.vo;

/**
 * 任务大厅中，显示卖家的基础信息
 * 
 * @author yqw
 *
 */
public class SalerInfVO {
	
	private String salerName;
	
	private int level;
	
	private int taskBaby;
	
	//交易记录－－本周
	private int thisWeek;
	
	//交易记录－－本月
	private int thisMonth;
	
	//交易记录－－本年
	private int thisYear;
	
	private boolean promise;

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTaskBaby() {
		return taskBaby;
	}

	public void setTaskBaby(int taskBaby) {
		this.taskBaby = taskBaby;
	}

	public int getThisWeek() {
		return thisWeek;
	}

	public void setThisWeek(int thisWeek) {
		this.thisWeek = thisWeek;
	}

	public int getThisMonth() {
		return thisMonth;
	}

	public void setThisMonth(int thisMonth) {
		this.thisMonth = thisMonth;
	}

	public int getThisYear() {
		return thisYear;
	}

	public void setThisYear(int thisYear) {
		this.thisYear = thisYear;
	}

	public boolean isPromise() {
		return promise;
	}

	public void setPromise(boolean promise) {
		this.promise = promise;
	}
	
	
}
