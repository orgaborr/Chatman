package com.orgabor.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean isRunning;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.input = new ObjectInputStream(clientSocket.getInputStream());
			this.output = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void run() {
		isRunning = true;

		while(isRunning) {
			try {
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!clientSocket.isConnected()) {
				stop();
				isRunning = false;
			}
		}
	}
	
	private void stop() {
		try {
			input.close();
			output.close();
			clientSocket.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
