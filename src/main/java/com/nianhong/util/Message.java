package com.nianhong.util;

import java.util.ArrayList;
import java.util.List;

public class Message {
	
	private boolean status;
	
	private List<String> content;

	public Message () {
		status = true;
		content = new ArrayList<String>();
	}
	
	public void addCont(String cont) {
		content.add(cont);
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}
	
}
