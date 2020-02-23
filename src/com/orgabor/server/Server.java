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
	private boolean isRunning;
	private int clientId = 1;
	private ObservableMap<Integer, Socket> clients;
	
	private static Server server = new Server();
	
	private Server() {
		//this.clients = FXCollections.observableArrayList();
		this.clients = FXCollections.observableHashMap();
	}
	
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
		clientIterator.remove();
	}
	
	Map<Integer, Socket> getClients() {
		return clients;
	}
	
	boolean getIsRunning() {
		return isRunning;
	}
	
	int getClientId() {
		return clientId;
	}
	
	void setClientId(int update) {
		this.clientId = update;
	}
	
}
