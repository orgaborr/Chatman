package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private static ServerSocket serverSocket;
	
	private static boolean isRunning = true;
	
	static void startServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			
			while(isRunning) {
				new Thread(new ClientHandler(serverSocket.accept())).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
