package com.orgabor;

import java.io.Serializable;

public class Message implements Serializable {
	private String messageText;
	private boolean ping;
	
	private static final long serialVersionUID = 1L;
	
	public Message(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageText() {
		return messageText;
	}
	
	public boolean isPing() {
		return ping;
	}
	
	void setToPing() {
		ping = true;
	}
	
}
