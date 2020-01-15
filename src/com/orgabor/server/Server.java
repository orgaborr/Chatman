package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private ServerSocket serverSocket;
	private static Server server = new Server();
	
	private boolean isRunning = true;
	
	private Server() {}
	
	void startServer(int port) {
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
	
	void setIsRunning(boolean state) {
		isRunning = state;
	}
}
