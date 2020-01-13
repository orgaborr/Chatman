package com.orgabor;

import java.io.Serializable;

public class Message implements Serializable{
	private String messageText;
	private String timeSent;
	
	private static final long serialVersionUID = 1L;
	
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(String date) {
		this.timeSent = date;
	}
	
	
}
