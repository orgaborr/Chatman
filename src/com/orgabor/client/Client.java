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
	
	//ip 92.249.164.76
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
				System.out.println("Connected to server");
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
	
	void send() {
		try {
			String messageText = ChatmanClient.clientController.getMessageField().getText();
			Message message = new Message(messageText);
			System.out.println("Sending message: " + message.getMessageText());
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			
			output.writeObject(message);
			
		} catch (IOException e) {
			System.out.println("Error on writing message: " + e.getMessage());
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
