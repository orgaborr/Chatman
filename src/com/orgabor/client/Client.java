package com.orgabor.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.orgabor.Message;

public class Client {
	private Socket clientSocket;
	private ObjectOutputStream output;
	
	private static Client client = new Client();
	
	private Client() {}
	
	static Client getInstance() {
		return client;
	}
	
	boolean connect(String ip, int port) {
		try {
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress(ip, port), 5000);
			if(clientSocket.isConnected()) {
				Thread listen = new Thread(new Listener(clientSocket));
				listen.setDaemon(true);
				listen.start();
				
				return true;
			}

		} catch (SocketTimeoutException e) {
			System.out.println("Connection timed out: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}

		return false;
	}
	
	boolean send(String text) {
		try {
			Message message = new Message(text);
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			
			output.writeObject(message);
			return true;
			
		} catch (IOException e) {
			System.out.println("Error on writing message: " + e.getMessage());
			return false;
		}
	}

	void closeConnections() {
		try {
			output.close();
			clientSocket.close();

		} catch (IOException e) {
			System.out.println("Error closing Client socket: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Error closing Client socket: " + e.getMessage());
		}
	}
	
}
