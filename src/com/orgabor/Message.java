package com.orgabor;

import java.io.Serializable;

public class Message implements Serializable {
	private String messageText;
	private String timeSent;
	
	private static final long serialVersionUID = 1L;
	
	public Message(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageText() {
		return messageText;
	}

	public String getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(String date) {
		this.timeSent = date;
	}	
}
