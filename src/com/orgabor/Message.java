package com.orgabor;

import java.io.Serializable;

public class Message implements Serializable {
	private String username;
	private String messageText;
	private boolean ping;
	
	private static final long serialVersionUID = 1L;
	
	public Message(String username, String messageText) {
		this.username = username;
		this.messageText = messageText;
	}

	public String getMessageText() {
		return messageText;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isPing() {
		return ping;
	}
	
	void setToPing() {
		ping = true;
	}
	
}
