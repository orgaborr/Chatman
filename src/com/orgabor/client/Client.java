package com.orgabor.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.orgabor.Message;

public class Client {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean isListening;
	private String incoming;
	
	//ip 92.249.164.76
	private static Client client = new Client("localhost", 5678);
	
	private Client(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			
			output = new ObjectOutputStream(
					 new BufferedOutputStream(
					     clientSocket.getOutputStream()
					     ));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void listen() {
		new Thread(() -> {
			isListening = true;
			try {
				System.out.println("listen method started");
				while(isListening) {
					input = new ObjectInputStream(
							new BufferedInputStream(
								clientSocket.getInputStream()
								));
					Message message = (Message) input.readObject();
					incoming = message.getTimeSent() + message.getMessageText();
				}

			} catch (SocketException e) {
				System.out.println("The socket was closed: " + e.getMessage());		
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	void closeConnections() {
		try {
			isListening = false;
			input.close();
			output.close();
			clientSocket.close();
			
		} catch (NullPointerException e) {
			System.out.println("Connection was not established, therefore could not be closed");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	static Client getInstance() {
		return client;
	}
	
}
