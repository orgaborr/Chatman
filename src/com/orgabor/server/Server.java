package com.orgabor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Server {
	private ServerSocket serverSocket;
	private int port = 5678;
	private boolean isRunning;
	private volatile int nextClientId = 1;
	private ObservableMap<Integer, Socket> clients;
	
	private static Server server = new Server();
	
	private Server() {
		this.clients = FXCollections.observableHashMap();
	}
	
	static Server getInstance() {
		return server;
	}
	
	void runServer() {
		new Thread(() -> {
			try {
				serverSocket = new ServerSocket(port);
				Socket client;
				isRunning = true;
				
				while(isRunning) {
					client = serverSocket.accept();
					int id = nextClientId;
					Thread handleClient = new Thread(new ClientHandler(client, id));
					handleClient.setDaemon(true);
					handleClient.start();
					ChatmanServer.serverController.printMessage("Client " + id + " connected");
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
			closeClientSockets();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeClientSockets() throws IOException {
		Iterator<Entry<Integer, Socket>> clientIterator = clients.entrySet().iterator();
		while(clientIterator.hasNext()) {
			clientIterator.next().getValue().close();
		}
	}
	
	Map<Integer, Socket> getClients() {
		return clients;
	}
	
	boolean getIsRunning() {
		return isRunning;
	}
	
	int getClientId() {
		return nextClientId;
	}
	
	void setNextClientId(int update) {
		this.nextClientId = update;
	}
	
}
