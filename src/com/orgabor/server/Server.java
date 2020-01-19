package com.orgabor.server;

import java.net.ServerSocket;

public class Server {
	private ServerSocket serverSocket;
	
	Server(int port) {
		try (ServerSocket socket = new ServerSocket(port)) {
			this.serverSocket = socket;
			
			startServer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startServer() throws Exception {
			System.out.println("Server running");
			
			while(true) {
				new Thread(new ClientHandler(serverSocket.accept())).start();
				System.out.println("Client connected");
		}
	}
}
