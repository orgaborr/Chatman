package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import com.orgabor.TimeTracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server {
	private ServerSocket serverSocket;
	private boolean isRunning;
	private ObservableList<Socket> clients = FXCollections.observableArrayList();
	
	private static Server server = new Server();
	
	private Server() {
		System.out.println("Server instantiated");
	}
	static Server getInstance() {
		return server;
	}
	
	void runServer(int port) {
		new Thread(() -> {
			try {
				serverSocket = new ServerSocket(port);
				isRunning = true;
				
				System.out.println("Server running");
				
				while(isRunning) {
					Thread handleClient = new Thread(new ClientHandler(serverSocket.accept()));
					handleClient.setDaemon(true);
					handleClient.start();
					System.out.println(TimeTracker.getTime() + " Client connected");
				}
				
			} catch (SocketException e) {
				System.out.println("Server closed down");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}).start();
	}
	
	void closeConnections() {
		isRunning = false;
		try {
			System.out.println("Server closeConnections() called");
			serverSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	List<Socket> getClients() {
		return clients;
	}
	
}
