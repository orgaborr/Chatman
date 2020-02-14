package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server {
	private ServerSocket serverSocket;
	private boolean isRunning;
	private ObservableList<Socket> clients = FXCollections.observableArrayList();
	
	private static Server server = new Server();
	
	private Server() {}
	
	static Server getInstance() {
		return server;
	}
	
	void runServer(int port) {
		new Thread(() -> {
			try {
				serverSocket = new ServerSocket(port);
				Socket client;
				isRunning = true;
				
				while(isRunning) {
					client = serverSocket.accept();
					Thread handleClient = new Thread(new ClientHandler(client));
					handleClient.setDaemon(true);
					handleClient.start();
					ChatmanServer.serverController.printMessage("Client connected (" +
																client.getRemoteSocketAddress().toString()
																.substring(1) + ")");
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
			for(Socket clientSocket : clients) {
				clientSocket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	List<Socket> getClients() {
		return clients;
	}
	
	boolean getIsRunning() {
		return isRunning;
	}
	
}
