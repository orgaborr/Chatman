package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class Server {
	private ServerSocket serverSocket;
	private static int port;
	
	private boolean isRunning = true;
	
	private static Server server = new Server(5678);
	
	private Server(int port) {
		Server.port = port;
	}
	
	void runServer() {
		new Thread(() ->  {
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
		});
	}
	
	void closeConnections() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isRunning = false;
	}
	
	static Server getInstance() {
		return server;
	}
}
