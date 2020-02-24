package com.orgabor.server;

import java.net.Socket;

public class ClientInfo {
	private int clientId;
	private Socket socket;
	private String username;
	
	ClientInfo(int clientId, Socket socket) {
		this.clientId = clientId;
		this.socket = socket;
	}

	public int getClientId() {
		return clientId;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
