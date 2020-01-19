package com.orgabor.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.orgabor.Message;

public class Client {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private String incoming;
	
	//ip 92.249.164.76
	private static Client client = new Client("localhost", 5678);
	
	private Client(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			input = new ObjectInputStream(clientSocket.getInputStream());
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void listen() {
		try {
			while(true) {
				Message message = (Message) input.readObject();
				incoming = message.getTimeSent() + message.getMessageText();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void closeConnections() {
		try {
			input.close();
			output.close();
			clientSocket.close();
			
		} catch (NullPointerException e) {
			System.out.println("Connection was not established");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	static Client getInstance() {
		return client;
	}
	
}
