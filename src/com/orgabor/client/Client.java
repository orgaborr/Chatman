package com.orgabor.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.orgabor.Message;

public class Client {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	
	private boolean isListening;
	
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
				return true;
			}

		} catch (SocketTimeoutException e) {
			System.out.println("Connection timed out: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}

		return false;
	}
	
	void listen() {
		System.out.println("listen method started");
		isListening = true;
		
		Thread listenThread = new Thread(() -> {
			try {
				while(isListening) {
					input = new ObjectInputStream(
							new BufferedInputStream(
								clientSocket.getInputStream()
								));
					Message message = (Message) input.readObject();
					System.out.println("Recieving: " + message.getMessageText());
					ChatmanClient.clientController.printMessage(message.getMessageText());
				}
				
			} catch (SocketException e) {
				System.out.println("Client: The socket was closed: " + e.getMessage());		
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found: " + e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		listenThread.setDaemon(true);
		listenThread.start();
	}
	
	void send() {
		try {
			String messageText = ChatmanClient.clientController.getMessageField().getText();
			Message message = new Message(messageText);
			System.out.println("Sending message: " + message.getMessageText());
			output = new ObjectOutputStream(
					 new BufferedOutputStream(
					 clientSocket.getOutputStream()
					 ));
			
			output.writeObject(message);
			ChatmanClient.clientController.getMessageField().setText("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	void closeConnections() {
		isListening = false;
		try {
			input.close();
			output.close();
			clientSocket.close();
			
		} catch (NullPointerException e) {
			System.out.println("Connection was not established, therefore could not be closed");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
