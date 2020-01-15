package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

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
				System.out.println("Client connected");
			}
			
		} catch (SocketException e) {
			System.out.println("Server closed down");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	static Server getInstance() {
		return server;
	}
	
	void closeServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isRunning = false;
	}
}
