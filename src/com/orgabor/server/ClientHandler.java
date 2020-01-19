package com.orgabor.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.orgabor.Message;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean isRunning;
	
	public ClientHandler(Socket acceptedSocket) {
		try (Socket socket = acceptedSocket;
			 ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
			
			this.clientSocket = socket;
			this.output = out;
			this.input = in;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			isRunning = true;
			while(isRunning) {
				System.out.println("CliendHandler started");

					Message message = (Message) input.readObject();

					output.writeObject(message);
						
				if(!clientSocket.isConnected()) {
					isRunning = false;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
