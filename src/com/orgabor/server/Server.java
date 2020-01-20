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
		System.out.println("Server instantiated");
	}
	
	void runServer() {
		new Thread(() ->  {
			try {
				serverSocket = new ServerSocket(port);
				
				System.out.println("Server running");
				
				while(isRunning) {
					Thread handleClient = new Thread(new ClientHandler(serverSocket.accept()));
					handleClient.setDaemon(true);
					handleClient.start();
					System.out.println("Client connected");
				}
				
			} catch (SocketException e) {
				System.out.println("Server closed down");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}).start();
	}
	
	void closeConnections() {
		try {
			System.out.println("Server closeConnections called");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isRunning = false;
	}
	
	static Server getInstance() {
		System.out.println("Server getInstance called");
		return server;
	}
}
