package com.orgabor.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private static Client client = new Client("92.249.164.76", 5678);
	
	private Client(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			input = new ObjectInputStream(clientSocket.getInputStream());
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void closeConnections() {
		try {
			input.close();
			output.close();
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static Client getInstance() {
		return client;
	}
	
}
