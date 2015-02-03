package com.nianhong.vo;

import java.util.Map;

import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.TaskGet;

public class TaskGetVO {

	private TaskGet taskGetModel;
	
	private Task taskModel;
	
	private SubTask subTaskModel;
	
	private String picPaths[];
	
	private Map<String, Object> personInf;

	public TaskGet getTaskGetModel() {
		return taskGetModel;
	}

	public void setTaskGetModel(TaskGet taskGetModel) {
		this.taskGetModel = taskGetModel;
	}

	public String[] getPicPaths() {
		return picPaths;
	}

	public void setPicPaths(String[] picPaths) {
		this.picPaths = picPaths;
	}

	public Map<String, Object> getPersonInf() {
		return personInf;
	}

	public void setPersonInf(Map<String, Object> personInf) {
		this.personInf = personInf;
	}

	public Task getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(Task taskModel) {
		this.taskModel = taskModel;
	}

	public SubTask getSubTaskModel() {
		return subTaskModel;
	}

	public void setSubTaskModel(SubTask subTaskModel) {
		this.subTaskModel = subTaskModel;
	}

	
}
