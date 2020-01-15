package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private int port = 5678;
	private static Server server = new Server();
	
	private boolean isRunning = true;
	
	private Server() {}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			
			System.out.println("Server running");
			
			while(isRunning) {
				new Thread(new ClientHandler(serverSocket.accept())).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static Server getInstance() {
		return server;
	}
	
	void startServer(int port) {
		
	}
	
	void setIsRunning(boolean state) {
		isRunning = state;
	}
}
