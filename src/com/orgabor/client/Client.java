package com.orgabor.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.orgabor.Message;

public class Client {
	private Socket clientSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private String incoming;
	
	//ip 92.249.164.76
	//private static Client client = new Client("localhost", 5678);
	
	Client(String ip, int port) {
		try (Socket socket = new Socket(ip, port);
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
			
			this.clientSocket = socket;
			this.output = out;
			this.input = in;
			
			listen();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void listen() {
		System.out.println("listen() called");
		new Thread(() -> {
			try {
				while(true) {

						Message message = (Message) input.readObject();
					
					//incoming = message.getMessageText();

				}
			} catch (Exception e) {
				e.printStackTrace();	
			}	

			System.out.println("listen loop stopped");
		}).start();
	}
	
}
