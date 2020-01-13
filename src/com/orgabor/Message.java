package com.orgabor;

import java.io.Serializable;

public class Message implements Serializable{
	private String messageText;
	private String date;
	
	private static final long serialVersionUID = 1L;
	
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
